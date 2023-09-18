package com.koshake.feature_list.data.api

import com.koshake.feature_list.data.model.HousesResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import javax.inject.Inject

class ListApi @Inject constructor(private val client: HttpClient) : IListApi {

    override suspend fun getHouses(): List<HousesResponse> =
        client.get("/v1/houses")
}

