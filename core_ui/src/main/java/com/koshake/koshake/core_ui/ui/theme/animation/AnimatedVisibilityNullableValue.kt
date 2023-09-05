package com.koshake.koshake.core_ui.ui.theme.animation

import androidx.compose.animation.*
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@Suppress("unused")
@Composable
fun <T : Any> ColumnScope.AnimatedVisibilityNullableValue(
    value: T?,
    modifier: Modifier = Modifier,
    enter: EnterTransition = fadeIn() + expandVertically(),
    exit: ExitTransition = fadeOut() + shrinkVertically(),
    content: @Composable AnimatedVisibilityScope.(T) -> Unit,
) {
    AnimatedVisibilityNullableValueImpl(
        value = value,
        modifier = modifier,
        enter = enter,
        exit = exit,
        content = content,
    )
}

@Composable
fun <T : Any> AnimatedVisibilityNullableValue(
    value: T?,
    modifier: Modifier = Modifier,
    enter: EnterTransition = fadeIn(),
    exit: ExitTransition = fadeOut(),
    content: @Composable AnimatedVisibilityScope.(T) -> Unit,
) {
    AnimatedVisibilityNullableValueImpl(
        value = value,
        modifier = modifier,
        enter = enter,
        exit = exit,
        content = content,
    )
}

@Composable
private fun <T : Any> AnimatedVisibilityNullableValueImpl(
    value: T?,
    enter: EnterTransition,
    exit: ExitTransition,
    content: @Composable AnimatedVisibilityScope.(T) -> Unit,
    modifier: Modifier = Modifier,
) {
    // Remember value to animate the case when value become null.
    // In this case we should set visible to `false`, but render content with last non-null value.
    var lastValue by remember { mutableStateOf(value) }
    if (value != null) lastValue = value

    AnimatedVisibility(
        visible = value != null,
        modifier = modifier,
        enter = enter,
        exit = exit,
        content = { lastValue?.let { content(it) } },
    )
}
