package com.koshake.feature_list.di

import androidx.lifecycle.ViewModelProvider
import com.koshake.core_api.app.FacadeComponentProvider
import com.koshake.viewmodel_base.viewmodel.ViewModelAssistedFactory
import com.koshake.viewmodel_base.viewmodel.ViewModelFactoryModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [HousesListModule::class, CharactersModule::class, ViewModelFactoryModule::class],
    dependencies = [FacadeComponentProvider::class]
)
interface ListComponent {
    val viewModelFactory: ViewModelProvider.Factory

    val viewModelAssistedFactory: ViewModelAssistedFactory

    companion object {

        fun create(providersFacade: FacadeComponentProvider): ListComponent {
            return DaggerListComponent
                .builder()
                .facadeComponentProvider(providersFacade)
                .build()
        }
    }
}

