package com.koshake.feature_list

import com.koshake.core_api.entity.Name
import com.koshake.feature_list.data.model.HousesResponse
import com.koshake.feature_list.data.model.NameResponse
import com.koshake.feature_list.domain.model.HousesEntity
import com.koshake.feature_list.ui.HousesItem
import com.koshake.feature_list.ui.HousesListItem

object HousesListDataFactory {

    fun getHousesListEntity() = listOf(
        HousesEntity(
            slug = "First",
            name = "First",
            members = getMembersList(),
        ),
        HousesEntity(
            slug = "Second",
            name = "Second",
            members = getMembersList(),
        )
    )

    private fun getMembersList() =
        listOf(
            Name(
                slug = "Kate",
                name = "Kate"
            ),
            Name(
                slug = "Jon",
                name = "Jon Snow"
            )
        )

    private fun getMembersResponseList() =
        listOf(
            NameResponse(
                slug = "Kate",
                name = "Kate"
            ),
            NameResponse(
                slug = "Jon",
                name = "Jon Snow"
            )
        )


    fun getHousesItemsList() = listOf(
        HousesItem(
            houseName = "First",
            houseSlug = "First",
            members = getMembersList()
        ),
        HousesItem(
            houseName = "Second",
            houseSlug = "Second",
            members = getMembersList()
        ),
    )

    fun getHousesListResponse() = listOf(
        HousesResponse(
            name = "First",
            slug = "First",
            members = getMembersResponseList()
        ),
        HousesResponse(
            name = "Second",
            slug = "Second",
            members = getMembersResponseList()
        )
    )


}
