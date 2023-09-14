package com.koshake.koshake.core_ui.ui.theme.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.koshake.koshake.core_ui.ui.theme.GameOfThronesTheme

@Composable
fun Loader(
    modifier: Modifier = Modifier,
    color: Color = GameOfThronesTheme.colors.buttonPrimary,
    backgroundColor: Color = GameOfThronesTheme.colors.backgroundPrimary,
    strokeWidth: Dp = 4.dp,
) {
    CircularProgressIndicator(
        color = color,
        strokeWidth = strokeWidth,
        backgroundColor = backgroundColor,
        modifier = modifier.size(40.dp),
    )
}

@Composable
fun LoaderLayout(
    modifier: Modifier = Modifier,
    showLoader: Boolean = false
) {
    AnimatedVisibility(
        visible = showLoader,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        Box(
            modifier = modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier.size(64.dp)
                    .align(Alignment.Center)
                    .background(color = GameOfThronesTheme.colors.backgroundPrimary, shape = CircleShape),
            ) {
                Loader(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}
