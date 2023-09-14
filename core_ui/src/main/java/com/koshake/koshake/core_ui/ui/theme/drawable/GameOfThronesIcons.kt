package com.koshake.koshake.core_ui.ui.theme.drawable

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.koshake.core_ui.R

object GameOfThronesIcons {
    val Back: Painter @Composable get() = painterResource(R.drawable.icon_back)
    val Home: Painter @Composable get() = painterResource(R.drawable.ic_home)
    val List: Painter @Composable get() = painterResource(R.drawable.ic_list)
    val Error: Painter @Composable get() = painterResource(R.drawable.icon_error)
}
