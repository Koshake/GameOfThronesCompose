package com.koshake.feature_list.ui.characters

import androidx.navigation.NavHostController

interface CharactersListController {

    fun onListItemClicked(navHostController: NavHostController, item: CharacterItem)
}
