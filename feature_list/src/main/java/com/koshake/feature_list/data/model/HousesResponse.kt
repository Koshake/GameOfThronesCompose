package com.koshake.feature_list.data.model

import kotlinx.serialization.Serializable

@Serializable
data class HousesResponse(
    val slug: String,
    val name: String,
    val members: List<PersonResponse>,
)
