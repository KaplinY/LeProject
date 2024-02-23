package com.littlelemon.leproject.feature.filter

import com.littlelemon.leproject.data.City

class FilterHelper {

    fun filterProvinces(type: FilterType, citiesList: List<City>) : List<City>{
        return when (type) {
            FilterType.All -> citiesList
            FilterType.Ontario -> citiesList.filter { city: City -> city.province == "Ontario" }
            FilterType.Quebec -> citiesList.filter { city: City -> city.province == "Quebec" }
            FilterType.BC -> citiesList.filter { city: City -> city.province == "British Columbia" }
            FilterType.Alberta -> citiesList.filter { city: City -> city.province == "Alberta" }
        }
    }
}