package com.koshake.feature_list.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.koshake.core_api.base.compositionLocal.LocalFacadeComponent
import com.koshake.feature_list.di.HousesListComponentHolder
import com.koshake.feature_list.ui.ListScreen
import com.koshake.feature_list_api.ListNavGraphHandler
import javax.inject.Inject

class ListNavGraphHandlerImpl @Inject constructor() : ListNavGraphHandler {

    private companion object {
        const val LIST_ROUTE = "houses"
    }

    override val listRoute: String = LIST_ROUTE

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(listRoute) {
            val listComponent = HousesListComponentHolder.init(LocalFacadeComponent.current)
            ListScreen(listComponent.viewModelFactory)
        }
    }
}
