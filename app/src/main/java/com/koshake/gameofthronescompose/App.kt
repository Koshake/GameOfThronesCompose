package com.koshake.gameofthronescompose

import android.app.Application
import com.koshake.core_api.app.AppWithFacade
import com.koshake.core_api.app.FacadeComponentProvider
import com.koshake.gameofthronescompose.di.FacadeComponent
import timber.log.Timber

class App: Application(), AppWithFacade {

    companion object {
        var facadeComponent: FacadeComponent? = null
    }

    override fun getFacade(): FacadeComponentProvider {
        return facadeComponent ?: FacadeComponent.init(this).also {
            facadeComponent = it
        }
    }

    override fun onCreate() {
        super.onCreate()
        getFacade()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
