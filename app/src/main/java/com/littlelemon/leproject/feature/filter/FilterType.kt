package com.littlelemon.leproject.feature.filter

sealed class FilterType {
    object All : FilterType()
    object Ontario : FilterType()
    object Quebec : FilterType()
    object BC : FilterType()
    object Alberta: FilterType()
}