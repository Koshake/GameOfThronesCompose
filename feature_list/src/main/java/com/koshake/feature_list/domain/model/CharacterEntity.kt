package com.koshake.feature_list.domain.model

import com.koshake.core_api.entity.Name
import com.koshake.feature_list.data.model.HousesResponse
import com.koshake.feature_list.data.model.toName

data class CharacterEntity(
    val characters: List<Name>
)

fun HousesResponse.toCharacterEntity() =
    CharacterEntity(
        characters = members.map { it.toName() }
    )
