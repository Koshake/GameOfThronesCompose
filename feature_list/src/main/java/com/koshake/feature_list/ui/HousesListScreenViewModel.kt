package com.koshake.feature_list.ui

import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.koshake.core_api.base.StatefulViewModel
import com.koshake.core_api.base.onEachContent
import com.koshake.core_api.base.onEachError
import com.koshake.feature_list.domain.repository.HousesListRepository
import com.koshake.feature_list.navigation.navigateToCharactersList
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import javax.inject.Inject

class HousesListScreenViewModel @Inject constructor(
    private val housesListRepository: HousesListRepository
) : StatefulViewModel<ListScreenState>(ListScreenState()), HousesListController {

    init {
        getHousesList()
    }

    private fun getHousesList() {
        housesListRepository.getHousesList()
            .onEach { updateState { copy(isLoading = it.isLoading) } }
            .onEachContent { housesList ->
                updateState {
                    copy(
                        isLoading = false,
                        isError = false,
                        housesList = housesList.map { house -> house.toHousesItem() }
                    )
                }
            }
            .onEachError {
                Timber.e("getHousesList Error: ${it.message}")
                updateState { copy(isLoading = false, isError = true) }
            }
            .launchIn(viewModelScope)
    }

    override fun onRefresh() {
        getHousesList()
    }

    override fun onListItemClicked(navController: NavHostController, item: HousesItem) {
        navigateToCharactersList(navController = navController, members = item.houseSlug)
    }

}

data class ListScreenState(
    val housesList: List<HousesItem> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false
)
