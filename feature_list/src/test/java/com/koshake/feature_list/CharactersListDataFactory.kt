package com.koshake.feature_list

import com.koshake.core_api.entity.Name
import com.koshake.feature_list.domain.model.CharacterEntity

object CharactersListDataFactory {

    const val house = "Stark"

    fun getCharactersListEntity() = CharacterEntity(
        characters = listOf(
            Name(
                name = "Jon Snow",
                slug = "jon"
            ),
            Name(
                name = "Sansa Stark",
                slug = "sansa"
            ),
        )
    )
}
