package com.koshake.feature_home.ui

import androidx.lifecycle.viewModelScope
import com.koshake.core_api.base.StatefulViewModel
import com.koshake.core_api.base.onEachContent
import com.koshake.core_api.base.onEachError
import com.koshake.feature_home.domain.HomeRepository
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
): StatefulViewModel<HomeScreenState>(HomeScreenState()), HomeScreenController {

    init {
        getRandomQuote()
    }

    private fun getRandomQuote() {
        homeRepository.getRandomQuote()
            .onEach { updateState { copy(isLoading = it.isLoading) } }
            .onEachContent {
                Timber.d("getRandomQuote Error: ${it.sentence}")
                updateState { copy(isLoading = false, isError = false, randomQuoteUi = it.toUi()) }
            }
            .onEachError {
                Timber.e("getRandomQuote Error: ${it.message}")
                updateState { copy(isLoading = false, isError = true) }
            }
            .launchIn(viewModelScope)
    }

    override fun onRefresh() {
        getRandomQuote()
    }
}

data class HomeScreenState(
    val randomQuoteUi: RandomQuoteUi = RandomQuoteUi(),
    val isLoading: Boolean = false,
    val isError: Boolean = false
)
