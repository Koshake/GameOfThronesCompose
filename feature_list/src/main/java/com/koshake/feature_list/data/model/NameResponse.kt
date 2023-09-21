package com.koshake.feature_list.data.model

import com.koshake.core_api.entity.Name
import kotlinx.serialization.Serializable

@Serializable
data class NameResponse(
    val name: String,
    val slug: String
)

fun NameResponse.toName() =
    Name(
        name = name,
        slug = slug
    )


