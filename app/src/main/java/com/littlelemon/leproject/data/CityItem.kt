package com.littlelemon.leproject.data

import androidx.annotation.DrawableRes

data class CityItem(
    val name: String,
    val province: String,
    @DrawableRes val image: Int
)

data class Cities(val items: List<CityItem>)
