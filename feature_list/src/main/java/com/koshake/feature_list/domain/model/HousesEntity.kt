package com.koshake.feature_list.domain.model

import com.koshake.feature_list.data.model.HousesResponse

data class HousesEntity(
    val slug: String,
    val name: String,
    val members: List<PersonEntity>,
)

fun HousesResponse.toHousesEntity() =
    HousesEntity(
        slug = slug,
        name = name,
        members = members.map {
            PersonEntity(
                name = it.name,
                slug = it.slug
            )
        }
    )
