package com.koshake.feature_home.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.koshake.feature_home.R
import com.koshake.koshake.core_ui.ui.theme.GameOfThronesDimension
import com.koshake.koshake.core_ui.ui.theme.GameOfThronesTheme
import com.koshake.koshake.core_ui.ui.theme.GameOfThronesTypography
import com.koshake.koshake.core_ui.ui.theme.view.ErrorStub
import com.koshake.koshake.core_ui.ui.theme.view.LoaderLayout
import com.koshake.koshake.core_ui.ui.theme.view.Toolbar
import com.koshake.koshake.core_ui.ui.theme.view.VSpacer

@Composable
fun HomeScreen(
    viewModelFactory: ViewModelProvider.Factory,
    viewModel: HomeViewModel = viewModel(factory = viewModelFactory),
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
                title = stringResource(R.string.home_toolbar_title)
            )
        }
    ) {
        when {
            state.value.isLoading -> LoaderLayout(showLoader = true)
            state.value.isError -> ErrorStub(modifier = Modifier.fillMaxSize(), onRefreshClicked = viewModel::onRefresh)
            else -> HomeScreenContent(state = state.value, controller = viewModel, modifier = Modifier.fillMaxSize())
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun HomeScreenContent(state: HomeScreenState, controller: HomeScreenController, modifier: Modifier) {

    val pullRefreshState = rememberPullRefreshState(state.isRefreshing,  controller::onRefresh)

    Column(
        modifier = modifier
            .padding(horizontal = GameOfThronesDimension.layoutHorizontalMargin)
            .pullRefresh(state = pullRefreshState),
    ) {

        PullRefreshIndicator(
            refreshing = state.isRefreshing,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.CenterHorizontally))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(1) {
                AnimatedVisibility(visible = state.randomQuoteUi.name.isNotEmpty()) {
                    Text(
                        text = String.format("%s:", state.randomQuoteUi.name),
                        style = GameOfThronesTypography.titleBook34,
                        textAlign = TextAlign.Start
                    )
                    VSpacer(size = 48.dp)
                }
                Text(
                    text = if (state.randomQuoteUi.sentence.isNotEmpty()) {
                        String.format("\"%s\"", state.randomQuoteUi.sentence)
                    } else {
                        stringResource(id = R.string.no_quotes)
                    },
                    style = GameOfThronesTypography.titleBook44,
                )
                VSpacer(size = 48.dp)
            }

        }
    }
}

