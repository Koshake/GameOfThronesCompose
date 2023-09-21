package com.koshake.feature_home.data.model

import com.koshake.core_api.entity.Name
import kotlinx.serialization.Serializable

@Serializable
data class PersonResponse(
    val name: String,
    val slug: String,
)

fun PersonResponse.toName() =
    Name(
        name = name,
        slug = slug
    )
