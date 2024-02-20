package com.littlelemon.leproject.compose.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.littlelemon.leproject.data.City
import com.littlelemon.leproject.viewmodels.CitiesViewModel


@Composable
fun HomeScreen(
    citiesViewModel: CitiesViewModel,
    cities: List<City>,
    onNextButtonClicked: () -> Unit = {},
    navController: NavController,
) {
    if (cities.isEmpty()) {
        EmptyHomeScreen(onNextButtonClicked)
    } else {
        SavedCitiesScreen(citiesViewModel, cities, onNextButtonClicked, navController)
    }
}





