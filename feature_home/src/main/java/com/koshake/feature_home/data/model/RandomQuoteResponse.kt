package com.koshake.feature_home.data.model

import kotlinx.serialization.Serializable

@Serializable
data class RandomQuoteResponse(
    val sentence: String? = null,
    val character: Name? = null,
    val house: Name? = null,
)

