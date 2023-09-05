package com.koshake.koshake.core_ui.ui.theme.color

private val gradientStatusDark = GradientColor(
    colorStart = AppColor.BlackSmoky,
    colorCenter = AppColor.GrayCharleston,
    colorEnd = AppColor.GrayLight
)

private val gradientToolbarDark = GradientColor(
    colorStart = AppColor.Black,
    colorCenter = AppColor.GrayCharleston,
    colorEnd = AppColor.GrayLight
)

val AppColorDarkPalette: AppThemeColors = AppThemeColors(
    isLight = false,
    active = AppColor.White,
    backgroundPrimary = AppColor.Black,
    backgroundSecondary = AppColor.BlackSmoky,
    backgroundTabbar = AppColor.Black_10,
    buttonPrimary = AppColor.Red,
    buttonPrimaryDisabled = AppColor.Gray,
    buttonPrimaryPressed = AppColor.RedLight,
    buttonPrimaryRipple = AppColor.RedLight,
    buttonSecondaryText = AppColor.RedSoft,
    buttonTertiaryText = AppColor.Black,
    divider = AppColor.White_20,
    error = AppColor.Orange,
    errorPressed = AppColor.OrangeOrioles,
    progressBar = AppColor.White_20,
    shimmerElement = AppColor.White_10,
    shimmerGradient = gradientStatusDark,
    textPrimary = AppColor.White,
    textPrimaryDisabled = AppColor.SilverQuick,
    textSecondary = AppColor.GrayDark,
    textTertiary = AppColor.Black,
    textToolbar = AppColor.White,
    toolbarColor = gradientToolbarDark,
    bottomBarColor = AppColor.BlackSmoky
)
