package com.koshake.koshake.core_ui.ui.theme.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonColors
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.koshake.koshake.core_ui.ui.theme.GameOfThronesTheme
import com.koshake.koshake.core_ui.ui.theme.GameOfThronesTypography
import com.koshake.koshake.core_ui.ui.theme.modifier.consumeTouches
import com.koshake.koshake.core_ui.ui.theme.modifier.rememberMinSize

@Composable
fun OutlineButtonLarge(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: LicardButtonColors = LicardButtonColors.outlineButtonColors(),
    maxLines: Int = 1,
    shape: Shape = RoundedCornerShape(10.dp),
    contentPadding: PaddingValues = LargeButtonContentPadding,
    loading: Boolean = false,
    icon: Painter? = null
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier.fillMaxWidth()
            .defaultMinSize(minHeight = LargeButtonHeight)
            .consumeTouches(loading)
            .rememberMinSize { _, _ -> !loading },
        enabled = enabled,
        elevation = null,
        shape = shape,
        colors = colors,
        border = BorderStroke(width = 1.dp, color = colors.contentColor(enabled).value),
        contentPadding = contentPadding,
    ) {
        if (loading) {
            CircularProgressIndicator(
                color = colors.contentColor(enabled).value,
                strokeWidth = 2.dp,
                modifier = Modifier.size(24.dp)
            )
        } else {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (icon != null) {
                    Icon(
                        painter = icon,
                        contentDescription = null,
                        tint = colors.contentColor(enabled).value
                    )
                    HSpacer(8.dp)
                }
                Text(
                    text = text,
                    maxLines = maxLines,
                    textAlign = TextAlign.Center,
                    style = GameOfThronesTypography.textMedium18,
                )
            }

        }
    }
}

private val LargeButtonHeight = 56.dp
private val LargeButtonContentPadding: PaddingValues = PaddingValues(vertical = 16.dp, horizontal = 16.dp)
private val LargeButtonLoadingPadding: PaddingValues = PaddingValues(vertical = 12.dp, horizontal = 12.dp)

@Immutable
data class LicardButtonColors(
    private val backgroundColor: Color,
    private val contentColor: Color,
    private val disabledBackgroundColor: Color,
    private val disabledContentColor: Color,
) : ButtonColors {

    @Composable
    override fun backgroundColor(enabled: Boolean): State<Color> {
        return rememberUpdatedState(if (enabled) backgroundColor else disabledBackgroundColor)
    }

    @Composable
    override fun contentColor(enabled: Boolean): State<Color> {
        return rememberUpdatedState(if (enabled) contentColor else disabledContentColor)
    }

    companion object {

        @Composable
        fun primaryButtonColors(
            backgroundColor: Color = GameOfThronesTheme.colors.buttonPrimary,
            contentColor: Color = GameOfThronesTheme.colors.textTertiary,
            disabledBackgroundColor: Color = GameOfThronesTheme.colors.buttonPrimaryDisabled,
            disabledContentColor: Color = contentColor,
        ): LicardButtonColors = LicardButtonColors(
            backgroundColor = backgroundColor,
            contentColor = contentColor,
            disabledBackgroundColor = disabledBackgroundColor,
            disabledContentColor = disabledContentColor,
        )

        @Composable
        fun outlineButtonColors(
            backgroundColor: Color = Color.Transparent,
            contentColor: Color = GameOfThronesTheme.colors.buttonPrimary,
        ): LicardButtonColors = LicardButtonColors(
            backgroundColor = backgroundColor,
            contentColor = contentColor,
            disabledBackgroundColor = backgroundColor,
            disabledContentColor = contentColor,
        )

        @Composable
        fun textButtonColors(
            backgroundColor: Color = Color.Transparent,
            contentColor: Color = GameOfThronesTheme.colors.buttonPrimary,
            disabledContentColor: Color = GameOfThronesTheme.colors.buttonSecondaryText
                .copy(alpha = ContentAlpha.disabled),
        ): LicardButtonColors = LicardButtonColors(
            backgroundColor = backgroundColor,
            contentColor = contentColor,
            disabledBackgroundColor = backgroundColor,
            disabledContentColor = disabledContentColor
        )
    }
}
