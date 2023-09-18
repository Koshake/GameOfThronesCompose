package com.koshake.feature_list.di

import com.koshake.feature_list.navigation.ListNavGraphHandlerImpl
import com.koshake.feature_list_api.ListNavGraphHandler
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
interface CreateHousesListExternalModule {

    @Binds
    @IntoMap
    @ClassKey(ListNavGraphHandler::class)
    fun bindHomeNavGraphHandler(provider: ListNavGraphHandlerImpl): Any
}
