package com.koshake.feature_list.ui.characters

import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.koshake.core_api.base.StatefulViewModel
import com.koshake.core_api.base.onEachContent
import com.koshake.core_api.base.onEachError
import com.koshake.feature_list.domain.repository.CharactersListRepository
import com.koshake.feature_list.navigation.navigateToQuotesList
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber

class CharactersViewModel @AssistedInject constructor(
    @Assisted house: String,
    private val charactersListRepository: CharactersListRepository
) : StatefulViewModel<CharactersScreenState>(CharactersScreenState(house = house)), CharactersListController {

    init {
        getCharactersList(house)
    }

    private fun getCharactersList(house: String) {
        charactersListRepository.getCharactersByHouse(house)
            .onEach { updateState { copy(isLoading = it.isLoading) } }
            .onEachContent { list ->
                updateState {
                    copy(
                        isLoading = false,
                        isError = false,
                        charactersList = list.characters.map { CharacterItem(it) }
                    )
                }
            }
            .onEachError {
                Timber.e("getCharactersList Error: ${it.message}")
                updateState { copy(isLoading = false, isError = true) }
            }
            .launchIn(viewModelScope)
    }

    override fun onListItemClicked(navHostController: NavHostController, item: CharacterItem) {
        navigateToQuotesList(navController = navHostController, slug = item.name.slug)
    }

    @AssistedFactory
    interface Factory {
        fun create(house: String): CharactersViewModel
    }
}

data class CharactersScreenState(
    val house: String,
    val charactersList: List<CharacterItem> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false
)
