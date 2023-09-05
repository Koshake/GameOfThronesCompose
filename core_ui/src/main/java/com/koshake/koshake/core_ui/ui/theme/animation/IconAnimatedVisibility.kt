package com.licard.b2b.ui.animation

import androidx.compose.animation.*
import androidx.compose.runtime.*

@Composable
fun IconAnimatedVisibility(
    visible: Boolean,
    content: @Composable AnimatedVisibilityScope.() -> Unit,
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn() + scaleIn(),
        exit = fadeOut() + scaleOut(),
        content = content,
    )
}