package com.koshake.core_impl.network

import android.util.Log
import dagger.Module
import dagger.Provides
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.DefaultRequest
import io.ktor.client.features.defaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.features.observer.ResponseObserver
import io.ktor.client.request.header
import io.ktor.client.request.host
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import kotlinx.serialization.json.Json
import javax.inject.Singleton

// TODO replace logger (Timber)
@Module
class NetworkModule {
    companion object {
        private const val TIME_OUT = 60_000
        private const val BASE_URL = "https://api.gameofthronesquotes.xyz"

        @Singleton
        @Provides
        fun provideKtorClient(): HttpClient {
            return HttpClient(Android) {
                defaultRequest {
                    host = BASE_URL
                    url {
                        protocol = URLProtocol.HTTPS
                    }
                }
                install(JsonFeature) {
                    serializer = KotlinxSerializer(Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    })

                    engine {
                        connectTimeout = TIME_OUT
                        socketTimeout = TIME_OUT
                    }
                }

                install(Logging) {
                    logger = object : Logger {
                        override fun log(message: String) {
                            Log.v("Logger Ktor =>", message)
                        }

                    }
                    level = LogLevel.ALL
                }

                install(ResponseObserver) {
                    onResponse { response ->
                        Log.d("HTTP status:", "${response.status.value}")
                    }
                }

                install(DefaultRequest) {
                    header(HttpHeaders.ContentType, ContentType.Application.Json)
                }
            }
        }
    }
}


