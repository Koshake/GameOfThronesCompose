package com.koshake.feature_list.data.model

import kotlinx.serialization.Serializable

@Serializable
data class QuotesResponse(
    val name: String,
    val slug: String,
    val house: NameResponse?,
    val quotes: List<String>,
)
