package com.koshake.core_api.app

import javax.inject.Provider

interface NavGraphProvider {
    fun navGraphMap(): Map<Class<*>, @JvmSuppressWildcards Provider<Any>>
}
