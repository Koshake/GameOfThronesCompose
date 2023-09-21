package com.koshake.feature_home.data.model

import kotlinx.serialization.Serializable

@Serializable
data class RandomQuoteResponse(
    val sentence: String? = null,
    val character: PersonResponse? = null,
    val house: PersonResponse? = null,
)

