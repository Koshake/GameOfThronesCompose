package com.koshake.feature_list.ui

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.koshake.koshake.core_ui.ui.theme.GameOfThronesDimension
import com.koshake.koshake.core_ui.ui.theme.GameOfThronesTheme
import com.koshake.koshake.core_ui.ui.theme.GameOfThronesTypography
import com.koshake.koshake.core_ui.ui.theme.Shapes

@Composable
internal fun HousesListItem(title: String, @DrawableRes icon: Int?, modifier: Modifier, onClick: () -> Unit) {
    Card(
        shape = Shapes.large,
        elevation = 10.dp,
        backgroundColor = GameOfThronesTheme.colors.cardBackground
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .clickable { onClick.invoke() }
        ) {
            AnimatedVisibility(visible = icon != null) {
                icon?.let {
                    Image(painter = painterResource(it), contentDescription = null, modifier.padding(end = 12.dp))
                }
            }
            Text(
                text = title,
                style = GameOfThronesTypography.titleBook28,
                modifier = Modifier.padding(GameOfThronesDimension.layoutMainPadding)
            )
        }
    }
}
