package com.koshake.main.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.koshake.core_api.app.FacadeComponentProvider
import com.koshake.core_api.navigator.NavGraphHandler
import com.koshake.feature_home_api.HomeNavGraphHandler

@Composable
fun AppNavGraph(
    homeNavGraphHandler: NavGraphHandler,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    facadeComponentProvider: FacadeComponentProvider

) {
    NavHost(navController = navController, startDestination = (homeNavGraphHandler as HomeNavGraphHandler).homeRoute) {
        register(
            navGraph = homeNavGraphHandler,
            navController = navController,
            modifier = modifier,
            facadeComponentProvider = facadeComponentProvider
        )

        // TODO Add Houses list screen
    }
}
