package com.koshake.koshake.core_ui.ui.theme.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.koshake.core_ui.R
import com.koshake.koshake.core_ui.ui.theme.GameOfThronesDimension
import com.koshake.koshake.core_ui.ui.theme.GameOfThronesTypography
import com.koshake.koshake.core_ui.ui.theme.drawable.GameOfThronesIcons

@Composable
fun ErrorStub(
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
