package com.koshake.main.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.koshake.main.R

enum class BottomTabs(
    @StringRes
    val title: Int,
    @DrawableRes
    val icon: Int,
    val route: String
) {
    HOME(
        R.string.title_home,
        R.drawable.ic_home,
        "home" // добавить через di
    ),
    SETTINGS(
        R.string.title_houses,
        R.drawable.ic_list,
        "houses" // добавить через di
    )
}
