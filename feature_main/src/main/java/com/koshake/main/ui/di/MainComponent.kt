package com.koshake.main.ui.di

import com.koshake.core_api.app.FacadeComponentProvider
import com.koshake.main.ui.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [MainModule::class],
    dependencies = [FacadeComponentProvider::class,]
)
interface MainComponent {

    companion object {

        fun create(providersFacade: FacadeComponentProvider): MainComponent {
            return DaggerMainComponent
                .builder()
                .facadeComponentProvider(providersFacade)
                .build()
        }
    }

    fun inject(mainActivity: MainActivity)
}
