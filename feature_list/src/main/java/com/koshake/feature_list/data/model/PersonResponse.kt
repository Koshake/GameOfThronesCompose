package com.koshake.feature_list.data.model

import kotlinx.serialization.Serializable

@Serializable
data class PersonResponse(
    val name: String,
    val slug: String
)
