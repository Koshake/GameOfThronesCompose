package com.koshake.feature_home.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.Scaffold
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
import com.koshake.koshake.core_ui.ui.theme.drawable.GameOfThronesIcons
import com.koshake.koshake.core_ui.ui.theme.view.LoaderLayout
import com.koshake.koshake.core_ui.ui.theme.view.OutlineButtonLarge
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
            else -> HomeScreenContent(state = state.value, modifier = Modifier.fillMaxSize())
        }
    }
}

@Composable
private fun HomeScreenContent(state: HomeScreenState, modifier: Modifier) {
    Column(
        modifier = modifier.padding(horizontal = GameOfThronesDimension.layoutHorizontalMargin),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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

@Composable
private fun ErrorStub(
    modifier: Modifier,
    onRefreshClicked: () -> Unit,
) {
    Column(modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Image(painter = GameOfThronesIcons.Error, contentDescription = null)
            Text(
                text = stringResource(id = R.string.something_went_wrong),
                style = GameOfThronesTypography.titleBook28,
                modifier = Modifier.padding(top = 24.dp)
            )
            Text(
                text = stringResource(id = R.string.error_message),
                style = GameOfThronesTypography.textBook18,
                modifier = Modifier.padding(top = 12.dp)
            )
        }
        OutlineButtonLarge(
            text = stringResource(id = R.string.refresh),
            onClick = onRefreshClicked,
            modifier = Modifier.padding(GameOfThronesDimension.layoutHorizontalMargin),
        )
    }
}
