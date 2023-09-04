package com.koshake.feature_home

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.koshake.feature_home_api.HomeNavGraphHandler
import javax.inject.Inject

class HomeNavGraphHandlerImpl @Inject constructor() : HomeNavGraphHandler {

    private companion object {
        const val HOME_ROUTE = "home"
    }

    override val homeRoute: String = HOME_ROUTE

    override fun registerGraph(navGraphBuilder: NavGraphBuilder, navController: NavHostController, modifier: Modifier) {
        navGraphBuilder.composable(homeRoute) {
            HomeScreen()
        }
    }
}