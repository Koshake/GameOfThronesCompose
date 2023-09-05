package com.koshake.feature_home.data.model

import kotlinx.serialization.Serializable

@Serializable
data class RandomQuoteResponse(
    val sentence: String,
    val character: Name,
    val house: Name,
)

