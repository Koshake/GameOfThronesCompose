package com.koshake.koshake.core_ui.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import com.koshake.koshake.core_ui.ui.theme.color.AppThemeColors

object GameOfThronesTheme {
    val colors: AppThemeColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current
}
