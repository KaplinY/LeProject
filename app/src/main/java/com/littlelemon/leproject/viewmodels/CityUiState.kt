package com.littlelemon.leproject.viewmodels

import com.littlelemon.leproject.data.City

data class CityUiState(
    var currentCity: City = City("", "", 0, 0)
)