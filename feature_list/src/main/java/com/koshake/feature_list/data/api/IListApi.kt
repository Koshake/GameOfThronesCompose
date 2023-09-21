package com.koshake.feature_list.data.api

import com.koshake.feature_list.data.model.HousesResponse

interface IListApi {
    suspend fun getHouses() : List<HousesResponse>

    suspend fun getCharacters(houseSlug: String) : List<HousesResponse>
}
