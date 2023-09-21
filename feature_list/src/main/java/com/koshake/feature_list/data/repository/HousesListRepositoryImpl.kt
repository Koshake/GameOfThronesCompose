package com.koshake.feature_list.data.repository

import com.koshake.core_api.base.Result
import com.koshake.feature_list.data.api.IListApi
import com.koshake.feature_list.domain.model.HousesEntity
import com.koshake.feature_list.domain.model.toHousesEntity
import com.koshake.feature_list.domain.repository.HousesListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class HousesListRepositoryImpl @Inject constructor(
    private val listApi: IListApi
) : HousesListRepository {

    override fun getHousesList(): Flow<Result<List<HousesEntity>>> {
        return flow {
            val list = listApi.getHouses().map { it.toHousesEntity() }
            emit(list)
        }.map<List<HousesEntity>, Result<List<HousesEntity>>> { Result.Content(it) }
            .onStart { emit(Result.Loading) }
            .catch { throwable ->
                emit(Result.Error(throwable))
            }
    }
}
