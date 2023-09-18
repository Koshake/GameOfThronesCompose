package com.koshake.feature_list_api

import com.koshake.core_api.navigator.NavGraphHandler

interface ListNavGraphHandler : NavGraphHandler {

    val listRoute: String
}
