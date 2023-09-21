package com.koshake.feature_home.domain

import com.koshake.core_api.entity.Name
import com.koshake.feature_home.data.model.RandomQuoteResponse
import com.koshake.feature_home.data.model.toName

data class RandomQuote(
    val sentence: String,
    val character: Name?,
    val house: Name?
)

fun RandomQuoteResponse.toRandomQuote() =
    RandomQuote(
        sentence = sentence ?: "",
        character = character?.toName(),
        house = house?.toName()
    )
