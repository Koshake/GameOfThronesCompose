package com.koshake.feature_home.domain

import com.koshake.core_api.base.Result
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun getRandomQuote(): Flow<Result<RandomQuote>>
}
