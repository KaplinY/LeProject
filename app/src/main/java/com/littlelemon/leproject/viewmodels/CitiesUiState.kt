package com.littlelemon.leproject.viewmodels

import com.littlelemon.leproject.data.CityItem

data class CitiesUiState(
    val savedcities: MutableList<CityItem> = mutableListOf(   ),
)
