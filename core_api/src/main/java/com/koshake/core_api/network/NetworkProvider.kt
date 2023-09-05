package com.koshake.core_api.network

import io.ktor.client.HttpClient

interface NetworkProvider {

    fun provideHttpClient() : HttpClient
}
