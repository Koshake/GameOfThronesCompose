package com.koshake.main.ui.navigation

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.koshake.koshake.core_ui.ui.theme.GameOfThronesDimension
import com.koshake.koshake.core_ui.ui.theme.GameOfThronesTheme
import com.koshake.koshake.core_ui.ui.theme.LocalColors
import java.util.Locale

@Composable
internal fun BottomBar(navController: NavController, tabs: Array<BottomTabs>) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: BottomTabs.HOME.route

    val routes = remember { BottomTabs.values().map { it.route }}

    if (currentRoute in routes) {
        BottomNavigation(
            modifier = Modifier.height(GameOfThronesDimension.bottomBarHeight),
            backgroundColor = GameOfThronesTheme.colors.bottomBarColor
        ) {
            tabs.forEach { tab ->
                BottomNavigationItem(
                    icon = { Icon(painterResource(tab.icon), contentDescription = null) },
                    label = { Text(stringResource(tab.title).uppercase(Locale.getDefault())) },
                    selected = currentRoute == tab.route,
                    onClick = {
                        if (tab.route != currentRoute) {
                            navController.navigate(tab.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    },
                    alwaysShowLabel = false,
                    selectedContentColor = LocalColors.current.active,
                    unselectedContentColor = LocalContentColor.current,
                    modifier = Modifier.navigationBarsPadding()
                )
            }
        }
    }
}
