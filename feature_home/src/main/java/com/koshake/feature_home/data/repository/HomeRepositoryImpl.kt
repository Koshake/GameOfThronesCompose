package com.koshake.feature_home.data.repository

import com.koshake.feature_home.data.api.HomeApi
import com.koshake.feature_home.domain.HomeRepository
import com.koshake.feature_home.domain.RandomQuote
import com.koshake.feature_home.domain.toRandomQuote
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val api: HomeApi): HomeRepository {

    override suspend fun getRandomQuote(): RandomQuote {
        return api.getRandomQuote().toRandomQuote()
    }
}
