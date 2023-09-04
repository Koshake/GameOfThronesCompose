package com.koshake.core_api.app

import android.content.Context

interface AppProvider {
    fun provideContext(): Context
}
