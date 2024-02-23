package com.littlelemon.leproject.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.littlelemon.leproject.data.City
import com.littlelemon.leproject.data.CityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CitiesViewModel @Inject constructor(
    private val cityRepository: CityRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CityUiState())
    val uiState: StateFlow<CityUiState> = _uiState.asStateFlow()

    val cityList: LiveData<List<City>> = cityRepository.allCities

    fun getAllCities() = viewModelScope.launch {
        cityRepository.getAllCities()
    }

    fun addCity(city: City) = viewModelScope.launch {
        cityRepository.addCity(city)
        getAllCities()
    }


    fun deleteCity(city: City) = viewModelScope.launch {
        cityRepository.deleteCity(city)
        getAllCities()
    }

    fun updateCurrentCity(city: City) = viewModelScope.launch {
        _uiState.update { currentState ->
            currentState.copy(
                currentCity = city
            )

        }
    }
}