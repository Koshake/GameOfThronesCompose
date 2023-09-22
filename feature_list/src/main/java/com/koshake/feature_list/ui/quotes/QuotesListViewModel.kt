package com.koshake.feature_list.ui.quotes

import androidx.lifecycle.viewModelScope
import com.koshake.core_api.base.StatefulViewModel
import com.koshake.core_api.base.onEachContent
import com.koshake.core_api.base.onEachError
import com.koshake.core_api.entity.Name
import com.koshake.feature_list.domain.repository.QuotesListRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import timber.log.Timber

class QuotesListViewModel @AssistedInject constructor(
    @Assisted characterEncoded: String,
    private val quotesListRepository: QuotesListRepository
) : StatefulViewModel<QuotesScreenState>(QuotesScreenState(characterName = Json.decodeFromString(characterEncoded))), QuotesListController {

    init {
        val characterNameDecoded: Name = Json.decodeFromString(characterEncoded)
        getQuotesList(characterNameDecoded.slug)
    }

    private fun getQuotesList(name: String) {
        quotesListRepository.getQuotesByCharacter(name)
            .onEach { updateState { copy(isLoading = it.isLoading) } }
            .onEachContent { response ->
                updateState {
                    copy(
                        isLoading = false,
                        isError = false,
                        quotesList = response.quotes
                    )
                }
            }
            .onEachError {
                Timber.e("getQuotesList Error: ${it.message}")
                updateState { copy(isLoading = false, isError = true) }
            }
            .launchIn(viewModelScope)
    }

    override fun onItemClicked() {
        // TODO Add later
    }

    override fun onRefresh() {
        getQuotesList(state.characterName.slug)
    }

    @AssistedFactory
    interface Factory {
        fun create(character: String): QuotesListViewModel
    }
}

data class QuotesScreenState(
    val characterName: Name,
    val quotesList: List<String> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false
)
