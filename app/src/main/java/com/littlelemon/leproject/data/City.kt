package com.littlelemon.leproject.data

import androidx.annotation.DrawableRes
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "city_table")
data class City(
    @PrimaryKey
    @ColumnInfo(name = "city") val city: String,
    @ColumnInfo(name = "province") val province: String,
    @DrawableRes @ColumnInfo(name = "picture") val picture: Int,
    @ColumnInfo(name = "description") val description: Int,
)

data class Cities(val items: List<City>)