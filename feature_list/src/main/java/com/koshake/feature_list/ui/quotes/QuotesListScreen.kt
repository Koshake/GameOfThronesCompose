package com.koshake.feature_list.ui.quotes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.koshake.core_ui.R
import com.koshake.koshake.core_ui.ui.theme.GameOfThronesDimension
import com.koshake.koshake.core_ui.ui.theme.GameOfThronesTheme
import com.koshake.koshake.core_ui.ui.theme.GameOfThronesTypography
import com.koshake.koshake.core_ui.ui.theme.Shapes
import com.koshake.koshake.core_ui.ui.theme.view.ErrorStub
import com.koshake.koshake.core_ui.ui.theme.view.LoaderLayout
import com.koshake.koshake.core_ui.ui.theme.view.NavigationIcon
import com.koshake.koshake.core_ui.ui.theme.view.Toolbar
import com.koshake.koshake.core_ui.ui.theme.view.VSpacer
import com.koshake.viewmodel_base.viewmodel.ViewModelAssistedFactory
import com.koshake.viewmodel_base.viewmodel.assistedViewModel

@Composable
fun QuotesListScreen(
    name: String,
    viewModelFactory: ViewModelAssistedFactory,
    navHostController: NavHostController
) {
    val viewModelAssistedFactory = remember<QuotesListViewModel.Factory> { viewModelFactory.assistedViewModelFactory() }
    val viewModel = assistedViewModel { viewModelAssistedFactory.create(name) }
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
                title = stringResource(R.string.quotes_characters, state.value.characterName.name)
            )
        }
    ) {
        when {
            state.value.isLoading -> LoaderLayout(showLoader = true)
            state.value.isError -> ErrorStub(modifier = Modifier.fillMaxSize(), onRefreshClicked = viewModel::onRefresh)
            else -> {
                QuotesScreenContent(
                    state = state.value,
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }
}

@Composable
private fun QuotesScreenContent(state: QuotesScreenState, modifier: Modifier) {
    LazyColumn(modifier = modifier, contentPadding = PaddingValues(12.dp)) {
        items(state.quotesList) { quote ->
            VSpacer(size = 12.dp)
            Card(
                shape = Shapes.medium,
                elevation = 10.dp,
                backgroundColor = GameOfThronesTheme.colors.backgroundPrimary
            ) {
                Text(
                    text = quote,
                    style = GameOfThronesTypography.textBook24,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(GameOfThronesDimension.layoutMainPaddingMedium,)
                )
            }
        }
    }
}
