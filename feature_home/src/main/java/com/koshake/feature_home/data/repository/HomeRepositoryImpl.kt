package com.koshake.feature_home.data.repository

import com.koshake.core_api.base.Result
import com.koshake.feature_home.data.api.HomeApi
import com.koshake.feature_home.domain.HomeRepository
import com.koshake.feature_home.domain.RandomQuote
import com.koshake.feature_home.domain.toRandomQuote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val api: HomeApi): HomeRepository {

    override fun getRandomQuote(): Flow<Result<RandomQuote>> {
        return flow {
            val quote = api.getRandomQuote().toRandomQuote()
            emit(quote)
        }.map<RandomQuote, Result<RandomQuote>> { Result.Content(it) }
            .onStart { emit(Result.Loading) }
            .catch { throwable ->
                emit(Result.Error(throwable))
            }
    }
}
