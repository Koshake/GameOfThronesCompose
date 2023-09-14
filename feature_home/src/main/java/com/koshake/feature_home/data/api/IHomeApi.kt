package com.koshake.feature_home.data.api

import com.koshake.feature_home.data.model.RandomQuoteResponse

interface IHomeApi {
    suspend fun getRandomQuote() : RandomQuoteResponse
}
