package com.koshake.feature_list.domain.repository

import com.koshake.core_api.base.Result
import com.koshake.feature_list.domain.model.CharacterEntity
import kotlinx.coroutines.flow.Flow

interface CharactersListRepository {

    fun getCharactersByHouse(house: String) : Flow<Result<CharacterEntity>>
}
