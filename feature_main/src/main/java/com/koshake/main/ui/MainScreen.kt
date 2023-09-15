package com.koshake.main.ui

import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.koshake.core_api.app.FacadeComponentProvider
import com.koshake.core_api.navigator.NavGraphHandler
import com.koshake.koshake.core_ui.ui.theme.LocalColors
import com.koshake.main.ui.navigation.AppNavGraph
import com.koshake.main.ui.navigation.BottomBar
import com.koshake.main.ui.navigation.BottomTabs

@Composable
internal fun MainScreen(
    homeNavGraphHandler: NavGraphHandler,
    listNavGraphHandler: NavGraphHandler
) {
    val tabs = remember { BottomTabs.values() }
    val navController = rememberNavController()
    Scaffold(
        backgroundColor = LocalColors.current.backgroundPrimary,
        bottomBar = { BottomBar(navController, tabs) },
        modifier = Modifier
            .navigationBarsPadding()
            .imePadding(),
    ) { innerPaddingModifier ->
        AppNavGraph(
            navController = navController,
            modifier = Modifier.padding(innerPaddingModifier),
            homeNavGraphHandler = homeNavGraphHandler,
            listNavGraphHandler = listNavGraphHandler
        )
    }
}
