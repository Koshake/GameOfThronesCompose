package com.koshake.feature_list.domain.model

import com.koshake.core_api.entity.Name
import com.koshake.feature_list.data.model.HousesResponse
import com.koshake.feature_list.data.model.toName

data class HousesEntity(
    val slug: String,
    val name: String,
    val members: List<Name>,
)

fun HousesResponse.toHousesEntity() =
    HousesEntity(
        slug = slug,
        name = name,
        members = members.map {
            it.toName()
        }
    )
