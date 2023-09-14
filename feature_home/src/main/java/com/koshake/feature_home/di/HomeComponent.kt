package com.koshake.feature_home.di

import androidx.lifecycle.ViewModelProvider
import com.koshake.core_api.app.FacadeComponentProvider
import com.koshake.viewmodel_base.viewmodel.ViewModelFactoryModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [HomeModule::class, ViewModelFactoryModule::class],
    dependencies = [FacadeComponentProvider::class]
)
interface HomeComponent {

    val viewModelFactory: ViewModelProvider.Factory

    companion object {

        fun create(providersFacade: FacadeComponentProvider): HomeComponent {
            return DaggerHomeComponent
                .builder()
                .facadeComponentProvider(providersFacade)
                .build()
        }
    }
}
