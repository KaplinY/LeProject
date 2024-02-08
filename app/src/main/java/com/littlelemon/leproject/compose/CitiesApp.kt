package com.littlelemon.leproject.compose

import android.app.Activity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.littlelemon.leproject.compose.cities.CitiesScreen
import com.littlelemon.leproject.compose.city.CityScreen
import com.littlelemon.leproject.compose.home.HomeScreen
import com.littlelemon.leproject.data.Cities
import com.littlelemon.leproject.data.CitiesList.citiesList
import kotlinx.coroutines.flow.MutableStateFlow
import com.littlelemon.leproject.data.CitiesList
import com.littlelemon.leproject.data.CityItem
import com.littlelemon.leproject.data.SavedCitiesList.savedcitiesList
import com.littlelemon.leproject.viewmodels.CitiesViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

enum class Screen() {
    Home,
    Cities,
    City,
}

@Composable
fun CitiesApp(
    viewModel: CitiesViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
){
    Scaffold {
        innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.name,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(route = Screen.Home.name){
                val savedcitiesState: MutableStateFlow<Cities> =
                    MutableStateFlow(Cities(savedcitiesList))

                val savedcities by savedcitiesState.collectAsState()
                HomeScreen(
                    cities = savedcities,
                    onNextButtonClicked = {
                    navController.navigate(Screen.Cities.name)
                },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                )
            }
            composable(route = Screen.Cities.name){
                val citiesState: MutableStateFlow<Cities> =
                    MutableStateFlow(Cities(citiesList))

                val cities by citiesState.collectAsState()

                CitiesScreen(
                    cities = cities,
                    onNextButtonClicked = {
                        navController.navigate(Screen.Home.name)
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                )
            }
            composable(route = Screen.City.name){

            }
        }
    }
}
