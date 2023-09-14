package com.koshake.main.ui.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.koshake.core_api.app.FacadeComponentProvider
import com.koshake.core_api.navigator.NavGraphHandler

fun NavGraphBuilder.register(
    navGraph: NavGraphHandler,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    facadeComponentProvider: FacadeComponentProvider
) {
    navGraph.registerGraph(
        navGraphBuilder = this,
        navController = navController,
        modifier = modifier,
        facadeComponentProvider = facadeComponentProvider
    )
}
