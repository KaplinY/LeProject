package com.littlelemon.leproject.data

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CityRepository @Inject constructor(
    private val cityDao: CityDao
) {
    val allCities = MutableLiveData<List<City>>()
    val foundCity = MutableLiveData<City>()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun addCity(newCity: City) {
        coroutineScope.launch(Dispatchers.IO) {
            cityDao.addCity(newCity)
        }
    }

    fun updateCityDetails(newCity: City) {
        coroutineScope.launch(Dispatchers.IO) {
            cityDao.updateCityDetails(newCity)
        }
    }

    fun getAllCities() {
        coroutineScope.launch(Dispatchers.IO) {
            allCities.postValue(cityDao.getAllCities())
        }
    }

    fun deleteCity(city: City) {
        coroutineScope.launch(Dispatchers.IO) {
            cityDao.deleteCity(city)
        }
    }

    fun findCityByName(cityName: String) {
        coroutineScope.launch(Dispatchers.IO) {
            foundCity.postValue(cityDao.findCityByName(cityName))
        }
    }

}