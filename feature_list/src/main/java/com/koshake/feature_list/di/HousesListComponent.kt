package com.koshake.feature_list.di

import androidx.lifecycle.ViewModelProvider
import com.koshake.core_api.app.FacadeComponentProvider
import com.koshake.viewmodel_base.viewmodel.ViewModelFactoryModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [HousesListModule::class, ViewModelFactoryModule::class],
    dependencies = [FacadeComponentProvider::class]
)
interface HousesListComponent {
    val viewModelFactory: ViewModelProvider.Factory

    companion object {

        fun create(providersFacade: FacadeComponentProvider): HousesListComponent {
            return DaggerHousesListComponent
                .builder()
                .facadeComponentProvider(providersFacade)
                .build()
        }
    }
}

