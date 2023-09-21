package com.koshake.feature_list.domain.repository

import com.koshake.feature_list.domain.model.QuotesEntity
import kotlinx.coroutines.flow.Flow
import com.koshake.core_api.base.Result

interface QuotesListRepository {

    fun getQuotesByCharacter(characterSlug: String): Flow<Result<QuotesEntity>>
}
