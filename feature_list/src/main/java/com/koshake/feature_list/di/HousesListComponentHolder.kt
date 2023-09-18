package com.koshake.feature_list.di

import com.koshake.core_api.app.FacadeComponentProvider

object HousesListComponentHolder {
    private var component: HousesListComponent? = null

    fun init(facade: FacadeComponentProvider) : HousesListComponent {
        return component ?: HousesListComponent.create(facade).also { component = it }
    }

    fun clear() {
        component = null
    }
}
