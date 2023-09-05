package com.koshake.feature_home.data.api

import com.koshake.feature_home.data.model.RandomQuoteResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import javax.inject.Inject

class HomeApi @Inject constructor(private val client: HttpClient) : IHomeApi {

    override suspend fun getRandomQuote(): RandomQuoteResponse =
        client.get("v1/random")

}
