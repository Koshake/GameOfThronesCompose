package com.koshake.feature_home.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Name(
    val name: String,
    val slug: String,
)
