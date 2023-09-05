package com.koshake.core_factory

import com.koshake.core_api.network.NetworkProvider
import com.koshake.core_impl.network.DaggerNetworkComponent

object CoreProvidersFactory {
    fun createNetworkBuilder(): NetworkProvider {
        return DaggerNetworkComponent.builder().build()
    }
}
