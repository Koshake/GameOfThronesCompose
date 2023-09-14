package com.koshake.feature_home.domain

import com.koshake.feature_home.data.model.Name
import com.koshake.feature_home.data.model.RandomQuoteResponse

data class RandomQuote(
    val sentence: String,
    val character: Name?,
    val house: Name?
)

fun RandomQuoteResponse.toRandomQuote() =
    RandomQuote(
        sentence = sentence ?: "",
        character = character,
        house = house
    )
