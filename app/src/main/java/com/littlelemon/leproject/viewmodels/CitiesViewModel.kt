package com.littlelemon.leproject.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.littlelemon.leproject.data.City
import com.littlelemon.leproject.data.CityItem
import com.littlelemon.leproject.data.CityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CitiesViewModel @Inject constructor(val cityRepository: CityRepository):
    ViewModel() {

    private val _uiState = MutableStateFlow(CityUiState())
    val uiState: StateFlow<CityUiState> = _uiState.asStateFlow()

    val cityList: LiveData<List<City>> = cityRepository.allCities
    val foundCity: LiveData<City> = cityRepository.foundCity


    fun getAllCities(){
        cityRepository.getAllCities()
    }

    fun addCity(city: City){
        cityRepository.addCity(city)
        getAllCities()
    }

    fun updateCityDetails(city: City){
        cityRepository.updateCityDetails(city)
        getAllCities()
    }

    fun deleteCity(city: City){
        cityRepository.deleteCity(city)
        getAllCities()
    }

    fun updateCurrentCity(city: City) {
        _uiState.update {currentState ->
            currentState.copy(
                currentCity = city
            )

        }
    }

}