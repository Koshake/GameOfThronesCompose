package com.koshake.koshake.core_ui.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.LocalContentColor
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import com.koshake.koshake.core_ui.ui.theme.color.AppColorDarkPalette
import com.koshake.koshake.core_ui.ui.theme.color.AppColorLightPalette
import com.koshake.koshake.core_ui.ui.theme.color.AppThemeColors

@Composable
fun GameOfThronesComposeTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = remember {
        if (darkTheme) {
            AppColorDarkPalette
        } else {
            AppColorLightPalette
        }
    }

    MaterialTheme(
            typography = Typography,
            shapes = Shapes
    ) {
        CompositionLocalProvider(
            LocalColors provides colors,
            LocalContentColor provides colors.textPrimary,
            content = content
        )
    }
}

val LocalColors = staticCompositionLocalOf<AppThemeColors> { error("No colors provided!") }
