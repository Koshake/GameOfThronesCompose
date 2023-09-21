package com.koshake.feature_list.di

import com.koshake.core_api.app.FacadeComponentProvider

object ListComponentHolder {
    private var component: ListComponent? = null

    fun init(facade: FacadeComponentProvider) : ListComponent {
        return component ?: ListComponent.create(facade).also { component = it }
    }

    fun clear() {
        component = null
    }
}
