package com.koshake.feature_home.di

import com.koshake.core_api.app.FacadeComponentProvider

object HomeComponentHolder {

    private var component: HomeComponent? = null

    fun init(facade: FacadeComponentProvider) : HomeComponent {
        return component ?: HomeComponent.create(facade).also { component = it }
    }

    fun clear() {
        component = null
    }

}
