package com.koshake.feature_list.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.koshake.core_api.base.compositionLocal.LocalFacadeComponent
import com.koshake.feature_list.di.ListComponentHolder
import com.koshake.feature_list.navigation.ListNavGraphHandlerImpl.Companion.CHARACTERS_LIST_ROUTE
import com.koshake.feature_list.navigation.ListNavGraphHandlerImpl.Companion.QUOTES_LIST_ROUTE
import com.koshake.feature_list.ui.HousesListScreenViewModel
import com.koshake.feature_list.ui.ListScreen
import com.koshake.feature_list.ui.characters.CharactersScreen
import com.koshake.feature_list.ui.characters.CharactersViewModel
import com.koshake.feature_list.ui.quotes.QuotesListScreen
import com.koshake.feature_list_api.ListNavGraphHandler
import javax.inject.Inject

class ListNavGraphHandlerImpl @Inject constructor() : ListNavGraphHandler {

    companion object {
        private const val LIST_BASE_ROUTE = "list"
        private const val HOUSES_ROUTE = "houses"
        const val CHARACTERS_LIST_ROUTE = "$LIST_BASE_ROUTE/characters"
        const val QUOTES_LIST_ROUTE = "$CHARACTERS_LIST_ROUTE/quotes"
        private const val houseArgumentKey = "house_arg"
        private const val characterArgumentKey = "character_arg"
    }

    override val listRootRoute: String = LIST_BASE_ROUTE

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {

        navGraphBuilder.navigation(
            route = listRootRoute,
            startDestination = HOUSES_ROUTE
        ) {
            composable(HOUSES_ROUTE) {
                val listComponent = ListComponentHolder.init(LocalFacadeComponent.current) // TODO REFACTORING!
                ListScreen(
                    viewModelFactory = listComponent.viewModelFactory,
                    navHostController = navController
                )
            }
            composable(
                route = "$CHARACTERS_LIST_ROUTE/{$houseArgumentKey}",
                arguments = listOf(
                    navArgument(houseArgumentKey) {
                        type = NavType.StringType
                    }
                )
            ) { backStackEntry ->
                val arguments = backStackEntry.arguments?.getString(houseArgumentKey)
                val listComponent = ListComponentHolder.init(LocalFacadeComponent.current)

                CharactersScreen(
                    viewModelFactory = listComponent.viewModelAssistedFactory,
                    house = arguments.orEmpty(),
                    navHostController = navController
                )
            }
            composable(
                route = "$QUOTES_LIST_ROUTE/{$characterArgumentKey}",
                arguments = listOf(
                    navArgument(characterArgumentKey) {
                        type = NavType.StringType
                    }
                )
            ) { backStackEntry ->
                val arguments = backStackEntry.arguments?.getString(characterArgumentKey)
                val listComponent = ListComponentHolder.init(LocalFacadeComponent.current)

                QuotesListScreen(
                    viewModelFactory = listComponent.viewModelAssistedFactory,
                    name = arguments.orEmpty()
                )
            }
        }
    }
}

internal fun HousesListScreenViewModel.navigateToCharactersList(navController: NavHostController, members: String) =
    navController.navigate(route = "${CHARACTERS_LIST_ROUTE}/$members")

internal fun CharactersViewModel.navigateToQuotesList(navController: NavHostController, slug: String) =
    navController.navigate(route = "${QUOTES_LIST_ROUTE}/$slug")
