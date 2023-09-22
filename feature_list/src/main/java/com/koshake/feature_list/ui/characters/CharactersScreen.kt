package com.koshake.feature_list.ui.characters

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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.koshake.core_ui.R
import com.koshake.koshake.core_ui.ui.theme.GameOfThronesDimension
import com.koshake.koshake.core_ui.ui.theme.GameOfThronesTheme
import com.koshake.koshake.core_ui.ui.theme.view.ErrorStub
import com.koshake.koshake.core_ui.ui.theme.view.LoaderLayout
import com.koshake.koshake.core_ui.ui.theme.view.NavigationIcon
import com.koshake.koshake.core_ui.ui.theme.view.Toolbar
import com.koshake.koshake.core_ui.ui.theme.view.VSpacer
import com.koshake.viewmodel_base.viewmodel.ViewModelAssistedFactory
import com.koshake.viewmodel_base.viewmodel.assistedViewModel

@Composable
fun CharactersScreen(
    house: String,
    viewModelFactory: ViewModelAssistedFactory,
    navHostController: NavHostController
) {

    val viewModelAssistedFactory = remember<CharactersViewModel.Factory> { viewModelFactory.assistedViewModelFactory() }
    val viewModel = assistedViewModel { viewModelAssistedFactory.create(house) }
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
                navigationIcon = NavigationIcon.Back,
                onNavigationClick = { navHostController.popBackStack() },
                title = stringResource(R.string.title_characters)
            )
        }
    ) {

        when {
            state.value.isLoading -> LoaderLayout(showLoader = true)
            state.value.isError -> ErrorStub(modifier = Modifier.fillMaxSize(), onRefreshClicked = viewModel::onRefresh)
            else -> {
                ListScreenContent(
                    state = state.value,
                    modifier = Modifier.fillMaxSize(),
                    charactersListController = viewModel,
                    navHostController = navHostController
                )
            }
        }
    }
}

@Composable
private fun ListScreenContent(
    state: CharactersScreenState,
    charactersListController: CharactersListController,
    navHostController: NavHostController,
    modifier: Modifier
) {
    LazyColumn(modifier = modifier, contentPadding = PaddingValues(12.dp)) {
        items(state.charactersList) { character ->
            VSpacer(size = 12.dp)
            CharactersListItem(
                title = character.name.name,
                subtitle = character.name.slug,
                modifier = Modifier.fillMaxWidth(),
            ) {
                charactersListController.onListItemClicked(
                    navHostController = navHostController,
                    item = character
                )
            }
        }
    }
}
