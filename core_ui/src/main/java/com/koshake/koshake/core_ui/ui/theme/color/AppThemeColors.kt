package com.koshake.koshake.core_ui.ui.theme.color

import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color

@Stable
data class AppThemeColors(
    val isLight: Boolean,
    val active: Color,
    val backgroundPrimary: Color,
    val backgroundSecondary: Color,
    val backgroundTabbar: Color,
    val cardBackground: Color,
    val buttonPrimary: Color,
    val buttonPrimaryDisabled: Color,
    val buttonPrimaryPressed: Color,
    val buttonPrimaryRipple: Color,
    val buttonSecondaryText: Color,
    val buttonTertiaryText: Color,
    val divider: Color,
    val error: Color,
    val errorPressed: Color,
    val progressBar: Color,
    val shimmerElement: Color,
    val shimmerGradient: GradientColor,
    val textPrimary: Color,
    val textPrimaryDisabled: Color,
    val textSecondary: Color,
    val textTertiary: Color,
    val textToolbar: Color,
    val toolbarColor: GradientColor,
    val bottomBarColor: Color
)

data class GradientColor(
    val colorStart: Color,
    val colorCenter: Color,
    val colorEnd: Color,
)
