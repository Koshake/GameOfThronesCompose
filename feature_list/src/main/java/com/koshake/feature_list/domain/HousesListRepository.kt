package com.koshake.feature_list.domain

import com.koshake.feature_list.domain.model.HousesEntity
import com.koshake.core_api.base.Result
import kotlinx.coroutines.flow.Flow

interface HousesListRepository {

    fun getHousesList() : Flow<Result<List<HousesEntity>>>
}
