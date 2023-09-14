package com.koshake.feature_home.ui

import com.koshake.feature_home.domain.RandomQuote

data class RandomQuoteUi(
    val sentence: String = "",
    val name: String = "",
)

fun RandomQuote.toUi() =
    RandomQuoteUi(
        sentence = sentence ,
        name = character?.name ?: ""
    )
