package com.koshake.feature_home.di

import com.koshake.feature_home.navigation.HomeNavGraphHandlerImpl
import com.koshake.feature_home_api.HomeNavGraphHandler
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
interface CreateHomeExternalModule {

    @Binds
    @IntoMap
    @ClassKey(HomeNavGraphHandler::class)
    fun bindHomeNavGraphHandler(provider: HomeNavGraphHandlerImpl): Any
}
