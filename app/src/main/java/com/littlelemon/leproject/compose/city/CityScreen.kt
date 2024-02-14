package com.littlelemon.leproject.compose.city

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import com.littlelemon.leproject.data.City
import com.littlelemon.leproject.viewmodels.CitiesViewModel

@Composable
fun CityScreen(citiesViewModel: CitiesViewModel){
    CityDetails(citiesViewModel)
}

@Composable
fun CityDetails(citiesViewModel: CitiesViewModel){


    val cityUiState by citiesViewModel.uiState.collectAsState()

    val city = cityUiState.currentCity
    Column {

        Text(text = city.city)
        Text(text = city.province)
        Image(painter = painterResource(id = city.picture), contentDescription = null)
    }
}
