package com.koshake.feature_home

import com.koshake.core_api.entity.Name
import com.koshake.feature_home.data.model.PersonResponse
import com.koshake.feature_home.data.model.RandomQuoteResponse
import com.koshake.feature_home.domain.RandomQuote
import com.koshake.feature_home.ui.RandomQuoteUi

object RandomQuoteFactory {

    fun getRandomQuoteEntity() =
        RandomQuote(
            sentence = "Hello!",
            character = Name(
                name = "Kate",
                slug = "Kate"
            ),
            house = Name(
                name = "House",
                slug = "House"
            )
        )

    fun getRandomQuoteUi() =
        RandomQuoteUi(
            sentence = "Hello!",
            name = "Kate"
        )

    fun getRandomQuoteResponse() =
        RandomQuoteResponse(
            sentence = "Hello!",
            character = PersonResponse(
                name = "Kate",
                slug = "Kate"
            ),
            house = PersonResponse(
                name = "House",
                slug = "House"
            )
        )
}
