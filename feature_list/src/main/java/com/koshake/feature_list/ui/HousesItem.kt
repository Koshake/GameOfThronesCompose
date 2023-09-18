package com.koshake.feature_list.ui

import androidx.annotation.DrawableRes
import com.koshake.feature_list.domain.model.HousesEntity

data class HousesItem(
    val houseName: String,
    @DrawableRes
    val icon: Int? = null
)

fun HousesEntity.toHousesItem() =
    HousesItem(
        houseName = name
    )
