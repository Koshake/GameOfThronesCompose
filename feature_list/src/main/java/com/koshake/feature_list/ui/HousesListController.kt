package com.koshake.feature_list.ui

import androidx.navigation.NavHostController

interface HousesListController {

    fun onRefresh()

    fun onListItemClicked(navController: NavHostController, item: HousesItem)
}
