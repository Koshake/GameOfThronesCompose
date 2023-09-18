package com.koshake.main.ui.di

import com.koshake.feature_home_api.HomeNavGraphHandler
import com.koshake.feature_list_api.ListNavGraphHandler
import dagger.Module
import dagger.Provides
import javax.inject.Provider

@Module
interface MainModule {

    companion object{

        @Provides
        fun provideHomeNavGraph(map: Map<Class<*>, @JvmSuppressWildcards Provider<Any>>): HomeNavGraphHandler {
            return map[HomeNavGraphHandler::class.java]!!.get() as HomeNavGraphHandler
        }

        @Provides
        fun provideListNavGraph(map: Map<Class<*>, @JvmSuppressWildcards Provider<Any>>): ListNavGraphHandler {
            return map[ListNavGraphHandler::class.java]!!.get() as ListNavGraphHandler
        }

    }
}
