package com.koshake.core_api.entity

import kotlinx.serialization.Serializable

@Serializable
data class Name(
    val name: String,
    val slug: String,
)
