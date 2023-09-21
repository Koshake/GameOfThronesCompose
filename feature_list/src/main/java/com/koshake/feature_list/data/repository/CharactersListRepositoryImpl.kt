package com.koshake.feature_list.data.repository

import com.koshake.core_api.base.Result
import com.koshake.feature_list.data.api.IListApi
import com.koshake.feature_list.domain.model.CharacterEntity
import com.koshake.feature_list.domain.model.toCharacterEntity
import com.koshake.feature_list.domain.repository.CharactersListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class CharactersListRepositoryImpl @Inject constructor(private val listApi: IListApi) : CharactersListRepository {

    override fun getCharactersByHouse(house: String): Flow<Result<CharacterEntity>> {
        return flow {
            val charactersList = listApi.getCharacters(house)[0].toCharacterEntity()
            emit(charactersList)
        }.map<CharacterEntity, Result<CharacterEntity>> { Result.Content(it) }
            .onStart { emit(Result.Loading) }
            .catch { throwable ->
                emit(Result.Error(throwable))
            }
    }

}
