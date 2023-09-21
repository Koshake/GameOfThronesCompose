package com.koshake.koshake.core_ui.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val MediumWeight = FontWeight(450)

val MaterialTypography: Typography = Typography(
    h1 = GameOfThronesTypography.titleBook44,
    h2 = GameOfThronesTypography.titleBook34,
    h3 = GameOfThronesTypography.titleBook28,
    subtitle1 = GameOfThronesTypography.textBook18,
    body1 = GameOfThronesTypography.textMedium18,
    body2 = GameOfThronesTypography.textMedium18,
    button = GameOfThronesTypography.textMedium18,
    caption = GameOfThronesTypography.captionBook14,
)

object GameOfThronesTypography {

    @Suppress("DEPRECATION")
    private val defaultStyle = TextStyle(
        platformStyle = PlatformTextStyle(
            includeFontPadding = false,
        ),
        fontStyle = FontStyle.Italic
    )

    val titleBook44 = defaultStyle.copy(
        fontWeight = FontWeight.Normal,
        fontSize = 44.sp,
        lineHeight = 48.sp,
    )
    val titleBook34 = defaultStyle.copy(
        fontWeight = FontWeight.Normal,
        fontSize = 34.sp,
        lineHeight = 40.sp,
    )
    val titleBook28 = defaultStyle.copy(
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp,
        lineHeight = 34.sp,
    )
    val textBook24 = defaultStyle.copy(
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        lineHeight = 30.sp,
    )

    val textBook24Bold = defaultStyle.copy(
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 30.sp,
    )

    val textMedium18 = defaultStyle.copy(
        fontWeight = MediumWeight,
        fontSize = 18.sp,
        lineHeight = 24.sp,
    )

    val textBook18 = defaultStyle.copy(
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        lineHeight = 24.sp,
    )
    val captionMedium16 = defaultStyle.copy(
        fontWeight = MediumWeight,
        fontSize = 16.sp,
        lineHeight = 20.sp,
    )
    val captionBook16 = defaultStyle.copy(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 22.sp,
    )
    val captionMedium14 = defaultStyle.copy(
        fontWeight = MediumWeight,
        fontSize = 14.sp,
        lineHeight = 16.sp,
    )
    val captionBook14 = defaultStyle.copy(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 16.sp,
    )
    val captionMedium12 = defaultStyle.copy(
        fontWeight = MediumWeight,
        fontSize = 12.sp,
        lineHeight = 14.sp,
    )
}

@Preview(showBackground = true)
@Composable
private fun TypographyPreview() {
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        Text("TitleBook44", style = GameOfThronesTypography.titleBook44)
        Text("TitleBook34", style = GameOfThronesTypography.titleBook34)
        Text("TitleBook28", style = GameOfThronesTypography.titleBook28)
        Text("TitleBook28_32", style = GameOfThronesTypography.titleBook28)
        Text("TextBook24", style = GameOfThronesTypography.textBook24)
        Text("TextMedium18", style = GameOfThronesTypography.textMedium18)
        Text("TextBook18", style = GameOfThronesTypography.textBook18)
        Text("CaptionMedium16", style = GameOfThronesTypography.captionMedium16)
        Text("CaptionBook16", style = GameOfThronesTypography.captionBook16)
        Text("CaptionMedium14", style = GameOfThronesTypography.captionMedium14)
        Text("CaptionBook14", style = GameOfThronesTypography.captionBook14)
        Text("CaptionMedium12", style = GameOfThronesTypography.captionMedium12)
    }
}
