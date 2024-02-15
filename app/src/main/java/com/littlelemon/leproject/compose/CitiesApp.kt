package com.littlelemon.leproject.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.littlelemon.leproject.compose.cities.CitiesScreen
import com.littlelemon.leproject.compose.city.CityScreen
import com.littlelemon.leproject.compose.home.HomeScreen
import com.littlelemon.leproject.data.Cities
import com.littlelemon.leproject.data.CitiesList.citiesList
import kotlinx.coroutines.flow.MutableStateFlow
//import com.littlelemon.leproject.viewmodels.CitiesViewModel
import com.littlelemon.leproject.data.City
import com.littlelemon.leproject.viewmodels.CitiesViewModel

sealed class Screen(val route: String) {
    object Home: Screen(route = "home_screen")
    object Cities: Screen(route = "cities_screen")
    object City: Screen(route = "city_screen")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CitiesAppBar(
    navigateUp: () -> Unit
){
    TopAppBar(
        title = { Text(text = "Canadian Cities") },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        navigationIcon = { IconButton(onClick =  navigateUp ) {
            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
                }
            },
        )
}

@Composable
fun CitiesApp(
    citiesViewModel: CitiesViewModel,
    navController: NavHostController = rememberNavController()
){

    citiesViewModel.getAllCities()
    val savedCitiesList: List<City> by citiesViewModel.cityList.observeAsState(initial = listOf())

    Scaffold(
        topBar = {
            CitiesAppBar(navigateUp = { navController.navigateUp()})
    }) {
        innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(route = Screen.Home.route){
                HomeScreen(
                    citiesViewModel = citiesViewModel,
                    cities = savedCitiesList,
                    onNextButtonClicked = {
                    navController.navigate(Screen.Cities.route)
                },
                    navController,
                )
            }
            composable(route = Screen.Cities.route){
                val citiesState: MutableStateFlow<Cities> =
                    MutableStateFlow(Cities(citiesList))

                val cities by citiesState.collectAsState()

                CitiesScreen(
                    citiesViewModel = citiesViewModel,
                    cities = cities,
                    savedCities = savedCitiesList,
                    onNextButtonClicked = {
                        navController.navigate(Screen.Home.route)
                    },
                    navController,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                )
            }
            composable(
                route = Screen.City.route,

                ){
                    CityScreen(
                        citiesViewModel = citiesViewModel,
                        )
            }
        }
    }
}
