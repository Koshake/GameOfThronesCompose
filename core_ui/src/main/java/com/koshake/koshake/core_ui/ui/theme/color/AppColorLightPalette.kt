package com.koshake.koshake.core_ui.ui.theme.color

private val gradientToolbarLight = GradientColor(
    colorStart = AppColor.White,
    colorCenter = AppColor.GrayLight,
    colorEnd = AppColor.GrayBright
)

val AppColorLightPalette: AppThemeColors = AppThemeColors(
    isLight = true,
    active = AppColor.White,
    backgroundPrimary = AppColor.White,
    backgroundSecondary = AppColor.GrayBG,
    backgroundTabbar = AppColor.Black,
    cardBackground = AppColor.SilverQuick,
    buttonPrimary = AppColor.Red,
    buttonPrimaryDisabled = AppColor.GrayLight,
    buttonPrimaryPressed = AppColor.RedDark,
    buttonPrimaryRipple = AppColor.RedDark,
    buttonSecondaryText = AppColor.Red,
    buttonTertiaryText = AppColor.Black,
    divider = AppColor.Black_20,
    error = AppColor.Orange,
    errorPressed = AppColor.OrangeDark,
    progressBar = AppColor.Black_10,
    shimmerElement = AppColor.Black_10,
    shimmerGradient = GradientColor(
        colorStart = AppColor.GrayBG,
        colorCenter = AppColor.GrayCultured,
        colorEnd = AppColor.GrayBright
    ),
    textPrimary = AppColor.Black,
    textPrimaryDisabled = AppColor.GrayDark,
    textSecondary = AppColor.GrayMiddle,
    textTertiary = AppColor.White,
    textToolbar = AppColor.Black,
    toolbarColor = gradientToolbarLight,
    bottomBarColor = AppColor.GrayDark,
    loaderColor = AppColor.BlackSmoky
)
