package com.koshake.feature_list.ui

import androidx.annotation.DrawableRes
import com.koshake.core_api.entity.Name
import com.koshake.feature_list.domain.model.HousesEntity

data class HousesItem(
    val houseName: String,
    val houseSlug: String,
    val members: List<Name>,
    @DrawableRes
    val icon: Int? = null
)

fun HousesEntity.toHousesItem() =
    HousesItem(
        houseName = name,
        houseSlug = slug,
        members = members
    )
