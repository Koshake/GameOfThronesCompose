package com.koshake.feature_list.domain.model

import com.koshake.feature_list.data.model.QuotesResponse

data class QuotesEntity(
    val name: String,
    val slug: String,
    val quotes: List<String>
)

fun QuotesResponse.toQuotesEntity() =
    QuotesEntity(
        name = name,
        slug = slug,
        quotes = quotes
    )
