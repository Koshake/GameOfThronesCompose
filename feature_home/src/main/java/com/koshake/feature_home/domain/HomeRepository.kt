package com.koshake.feature_home.domain

interface HomeRepository {
    suspend fun getRandomQuote(): RandomQuote
}
