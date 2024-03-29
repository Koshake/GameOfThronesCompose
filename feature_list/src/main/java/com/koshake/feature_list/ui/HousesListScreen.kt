package com.koshake.feature_list.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.koshake.koshake.core_ui.ui.theme.GameOfThronesDimension
import com.koshake.koshake.core_ui.ui.theme.GameOfThronesTheme
import com.koshake.koshake.core_ui.ui.theme.view.ErrorStub
import com.koshake.koshake.core_ui.ui.theme.view.LoaderLayout
import com.koshake.koshake.core_ui.ui.theme.view.Toolbar
import com.koshake.koshake.core_ui.ui.theme.view.VSpacer

@Composable
fun ListScreen(
    viewModelFactory: ViewModelProvider.Factory,
    navHostController: NavHostController,
    viewModel: HousesListScreenViewModel = viewModel(factory = viewModelFactory),
) {
    val state = viewModel.stateFlow.collectAsState()
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(GameOfThronesTheme.colors.backgroundPrimary)
            .systemBarsPadding()
            .imePadding()
            .padding(bottom = GameOfThronesDimension.bottomBarHeight),
        topBar = {
            Toolbar(
                title = stringResource(com.koshake.core_ui.R.string.title_houses)
            )
        }
    ) {
        when {
            state.value.isLoading -> LoaderLayout(showLoader = true)
            state.value.isError -> ErrorStub(modifier = Modifier.fillMaxSize(), onRefreshClicked = viewModel::onRefresh)
            else -> ListScreenContent(
                state = state.value,
                navHostController = navHostController,
                listScreenController = viewModel,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
private fun ListScreenContent(state: ListScreenState, listScreenController: HousesListController, navHostController: NavHostController, modifier: Modifier) {
    LazyColumn(modifier = modifier, contentPadding = PaddingValues(12.dp)) {
        items(state.housesList) { house ->
            VSpacer(size = 12.dp)
            HousesListItem(
                title = house.houseName,
                icon = house.icon,
                modifier = Modifier.fillMaxWidth(),
            ) {
                listScreenController.onListItemClicked(navController = navHostController, item = house)
            }
        }
    }
}
