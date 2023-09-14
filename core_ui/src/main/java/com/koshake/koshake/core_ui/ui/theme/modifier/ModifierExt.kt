package com.koshake.koshake.core_ui.ui.theme.modifier

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp

fun Modifier.surface(
    backgroundColor: Color,
    shape: Shape,
    elevation: Dp = 0.dp,
    onClick: (() -> Unit)? = null,
): Modifier {
    return this
        .shadow(elevation, shape)
        .background(color = backgroundColor, shape = shape)
        .clip(shape)
        .then(if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier)
}

fun Modifier.surface(
    backgroundBrush: Brush,
    shape: Shape,
    elevation: Dp = 0.dp,
    onClick: (() -> Unit)? = null,
): Modifier {
    return this
        .shadow(elevation, shape)
        .background(brush = backgroundBrush, shape = shape)
        .clip(shape)
        .then(if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier)
}

@OptIn(ExperimentalComposeUiApi::class)
fun Modifier.consumeTouches(consume: Boolean): Modifier = pointerInteropFilter { consume }

fun Modifier.rememberMinSize(predicate: (old: IntSize, new: IntSize) -> Boolean): Modifier = composed(
    inspectorInfo = debugInspectorInfo {
        name = "rememberMinSize"
    }
) {
    var contentSize by remember { mutableStateOf(IntSize.Zero) }
    val density = LocalDensity.current

    Modifier
        .onGloballyPositioned { coordinates ->
            val size = coordinates.size
            if (predicate(contentSize, size)) contentSize = size
        }
        .sizeIn(
            minHeight = with(density) { contentSize.height.toDp() },
            minWidth = with(density) { contentSize.width.toDp() },
        )
}
