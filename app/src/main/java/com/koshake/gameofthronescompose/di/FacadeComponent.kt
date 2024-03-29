package com.koshake.gameofthronescompose.di

import android.app.Application
import com.koshake.core_api.app.AppProvider
import com.koshake.core_api.app.FacadeComponentProvider
import com.koshake.core_api.network.NetworkProvider
import com.koshake.core_factory.CoreProvidersFactory
import com.koshake.feature_home.di.CreateHomeExternalModule
import com.koshake.feature_list.di.CreateHousesListExternalModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [AppProvider::class, NetworkProvider::class],
    modules = [CreateHomeExternalModule::class, CreateHousesListExternalModule::class]
)
interface FacadeComponent : FacadeComponentProvider {

    companion object {

        fun init(application: Application): FacadeComponent =
            DaggerFacadeComponent
                .builder()
                .appProvider(AppComponent.create(application))
                .networkProvider(CoreProvidersFactory.createNetworkBuilder())
                .build()
    }
}
