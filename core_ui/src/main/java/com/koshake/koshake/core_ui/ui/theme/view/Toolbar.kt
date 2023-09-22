package com.koshake.koshake.core_ui.ui.theme.view

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.koshake.core_ui.R
import com.koshake.koshake.core_ui.ui.theme.GameOfThronesComposeTheme
import com.koshake.koshake.core_ui.ui.theme.GameOfThronesDimension
import com.koshake.koshake.core_ui.ui.theme.GameOfThronesTheme
import com.koshake.koshake.core_ui.ui.theme.GameOfThronesTypography
import com.koshake.koshake.core_ui.ui.theme.animation.AnimatedVisibilityNullableValue
import com.koshake.koshake.core_ui.ui.theme.color.GradientColor
import com.koshake.koshake.core_ui.ui.theme.drawable.GameOfThronesIcons
import com.koshake.koshake.core_ui.ui.theme.modifier.surface
import com.koshake.koshake.core_ui.ui.theme.preview.ThemePreviewParameter
import kotlin.math.roundToInt

val AppBarHeight: Dp = 56.dp
val NavigationIconSize: Dp = 24.dp
private val IconCornerPadding = GameOfThronesDimension.toolbarHorizontalMargin - (GameOfThronesDimension.minTouchSize - 32.dp) / 2

@Composable
fun Toolbar(
    modifier: Modifier = Modifier,
    title: String = "",
    enableShadow: Boolean = false
) {
    Toolbar(
        title = title,
        color = GameOfThronesTheme.colors.toolbarColor,
        navigationIcon = null,
        onNavigationClick = {},
        modifier = modifier,
        enableShadow = enableShadow
    )
}

@Composable
fun Toolbar(
    modifier: Modifier = Modifier,
    color: GradientColor = GameOfThronesTheme.colors.toolbarColor,
    contentPadding: PaddingValues = PaddingValues(),
    content: @Composable BoxScope.() -> Unit,
) {
    Box(
        modifier
            .surface(backgroundBrush = getGradientBrush(colors = color), shape = RectangleShape)
            .padding(contentPadding)
            .fillMaxWidth()
            .height(AppBarHeight)
            .padding(bottom = 4.dp),
        contentAlignment = Alignment.CenterStart,
        content = content,
    )
}

@Composable
fun Toolbar(
    navigationIcon: NavigationIcon?,
    onNavigationClick: () -> Unit,
    modifier: Modifier = Modifier,
    color: GradientColor = GameOfThronesTheme.colors.toolbarColor,
    title: String = "",
    enableShadow: Boolean = false
) {
    val elevation = if (enableShadow) 6.dp else 0.dp
    val bottomPadding = if (enableShadow) 2.dp else 0.dp
    Toolbar(
        modifier = modifier
            .padding(bottom = bottomPadding)
            .shadow(elevation),
        color = color,
    ) {
        Row(Modifier.fillMaxHeight(), verticalAlignment = Alignment.CenterVertically) {
            NavigationIcon(navigationIcon, onNavigationClick)

            Column(
                Modifier
                    .padding(horizontal = GameOfThronesDimension.layoutHorizontalMargin)
                    .weight(1f),
            ) {
                Text(
                    text = title,
                    style = GameOfThronesTypography.textBook24,
                    color = GameOfThronesTheme.colors.textToolbar,
                )
            }
        }
    }
}

@Composable
fun ScrollableAppBar(
    modifier: Modifier = Modifier,
    title: String = "",
    navigationIcon: NavigationIcon?,
    onNavigationClick: () -> Unit,
    @DrawableRes backgroundImageId: Int,
    scrollableAppBarHeight: Dp,
    toolbarOffsetHeightPx: MutableState<Float>
) {

    val maxOffsetHeightPx = with(LocalDensity.current) { scrollableAppBarHeight.roundToPx().toFloat() - AppBarHeight.roundToPx().toFloat() }
    val titleOffsetWidthReferenceValue = with(LocalDensity.current) { NavigationIconSize.roundToPx().toFloat() }

    Box(modifier = Modifier
        .height(scrollableAppBarHeight)
        .offset {
            IntOffset(x = 0, y = toolbarOffsetHeightPx.value.roundToInt())
        }
        .fillMaxWidth()
    ) {
        Image(painter = painterResource(id = backgroundImageId), contentDescription = "background", contentScale = ContentScale.FillBounds)

        Row(
            modifier = modifier
                .offset {
                    IntOffset(
                        x = 0,
                        y = -toolbarOffsetHeightPx.value.roundToInt()
                    )
                }
                .height(AppBarHeight)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            NavigationIcon(navigationIcon, onNavigationClick)
        }

        Box(
            modifier = Modifier
                .height(AppBarHeight)
                .fillMaxWidth()
                .align(Alignment.BottomStart)
                .offset {
                    IntOffset(
                        x = -((toolbarOffsetHeightPx.value / maxOffsetHeightPx) * titleOffsetWidthReferenceValue).roundToInt(),
                        y = 0
                    )
                },
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = title,
                style = GameOfThronesTypography.textMedium18,
                color = GameOfThronesTheme.colors.textToolbar,
            )
        }
    }
}

@Composable
private fun NavigationIcon(
    navigationIcon: NavigationIcon?,
    onNavigationClick: () -> Unit,
) {
    AnimatedVisibilityNullableValue(navigationIcon) { icon ->
        IconButton(
            onClick = onNavigationClick,
            modifier = Modifier
                .padding(start = IconCornerPadding - icon.innerPadding),
            enabled = navigationIcon != null,
            content = { icon() },
        )
    }
}

enum class NavigationIcon(
    private val painter: @Composable () -> Painter,
    val innerPadding: Dp,
) {
    Back(
        painter = { GameOfThronesIcons.Back },
        innerPadding = 4.dp,
    );

    @Composable
    operator fun invoke() {
        Icon(
            painter = painter(),
            contentDescription = null,
            tint = GameOfThronesTheme.colors.textPrimary,
            modifier = Modifier.size(NavigationIconSize)
        )
    }
}

@Suppress("MagicNumber")
@Composable
private fun getGradientBrush(
    colors: GradientColor
): Brush {

    return Brush.linearGradient(
        colors = listOf(
            colors.colorStart,
            colors.colorCenter,
            colors.colorEnd
        ),
        start = Offset.Zero,
        end = Offset.Infinite
    )
}


@Preview
@Composable
private fun Preview(@PreviewParameter(ThemePreviewParameter::class) useDarkMode: Boolean) {
    GameOfThronesComposeTheme(useDarkMode) {
        Column {
            Toolbar(title = "Title")
            Divider()
            Toolbar(
                title = "Title and navigation icon",
                navigationIcon = NavigationIcon.Back,
                onNavigationClick = {}
            )
            Divider()
            Toolbar(
                title = "",
                navigationIcon = NavigationIcon.Back,
                onNavigationClick = {},
            )
            Divider()
            ScrollableAppBar(
                title = "Title and navigation icon",
                navigationIcon = NavigationIcon.Back,
                onNavigationClick = {},
                backgroundImageId = R.drawable.houses,
                scrollableAppBarHeight = 150.dp,
                toolbarOffsetHeightPx = mutableStateOf(0f)
            )
        }
    }
}
