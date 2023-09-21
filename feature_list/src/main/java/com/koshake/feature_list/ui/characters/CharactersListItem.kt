package com.koshake.feature_list.ui.characters

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.koshake.koshake.core_ui.ui.theme.GameOfThronesTheme
import com.koshake.koshake.core_ui.ui.theme.GameOfThronesTypography
import com.koshake.koshake.core_ui.ui.theme.Shapes
import com.koshake.koshake.core_ui.ui.theme.view.VSpacer

@Composable
internal fun CharactersListItem(title: String, subtitle: String, modifier: Modifier, onItemClicked: () -> Unit) {
    Card(
        shape = Shapes.medium,
        elevation = 10.dp,
        backgroundColor = GameOfThronesTheme.colors.backgroundPrimary
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .clickable { onItemClicked.invoke() }) {
            Text(
                text = title,
                style = GameOfThronesTypography.textBook24Bold,
                modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp)
            )
            VSpacer(size = 16.dp)
            Text(
                text = subtitle,
                style = GameOfThronesTypography.captionBook14,
                modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
            )
        }
    }
}
