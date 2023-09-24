package com.koshake.feature_list.data.repository

import com.koshake.core_api.base.Result
import com.koshake.feature_list.data.api.IListApi
import com.koshake.feature_list.domain.model.QuotesEntity
import com.koshake.feature_list.domain.model.toQuotesEntity
import com.koshake.feature_list.domain.repository.QuotesListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class QuotesListRepositoryImpl @Inject constructor(private val listApi: IListApi) : QuotesListRepository {

    override fun getQuotesByCharacter(characterSlug: String): Flow<Result<QuotesEntity>> {
        return flow {
            val quotes = listApi.getQuotes(characterSlug)[0].toQuotesEntity()
            emit(quotes)
        }.map<QuotesEntity, Result<QuotesEntity>> { Result.Content(it) }
            .onStart { emit(Result.Loading) }
            .catch { throwable ->
                emit(Result.Error(throwable))
            }
    }

}
