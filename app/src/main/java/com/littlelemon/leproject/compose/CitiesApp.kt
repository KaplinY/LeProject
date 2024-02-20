package com.littlelemon.leproject.compose

//import com.littlelemon.leproject.viewmodels.CitiesViewModel
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.littlelemon.leproject.R
import com.littlelemon.leproject.compose.cities.CitiesScreen
import com.littlelemon.leproject.compose.city.CityScreen
import com.littlelemon.leproject.compose.home.HomeScreen
import com.littlelemon.leproject.data.Cities
import com.littlelemon.leproject.data.CitiesList.citiesList
import com.littlelemon.leproject.data.City
import com.littlelemon.leproject.feature.filter.FilterHelper
import com.littlelemon.leproject.feature.filter.FilterType
import com.littlelemon.leproject.viewmodels.CitiesViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CitiesAppBar(
    citiesState: MutableStateFlow<Cities>,
    currentScreen: Screen,
    navigateUp: () -> Unit
) {

    var mDisplayMenu by remember { mutableStateOf(false) }
    if (currentScreen == Screen.Cities) {
        TopAppBar(
            title = { Text(text = stringResource(id = R.string.canadian_cities)) },
            colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ),
            navigationIcon = {
                IconButton(onClick = navigateUp) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
                }
            },
            actions = {
                IconButton(onClick = { mDisplayMenu = !mDisplayMenu }) {
                    Icon(Icons.Default.MoreVert, "")
                }
                DropdownMenu(
                    expanded = mDisplayMenu,
                    onDismissRequest = { mDisplayMenu = false }
                ) {
                    FilterDropDownMenuItem(
                        text = "Show All",
                        citiesState = citiesState,
                        filterType = FilterType.All
                    )
                    FilterDropDownMenuItem(
                        text = "Ontario",
                        citiesState = citiesState,
                        filterType = FilterType.Ontario
                    )
                    FilterDropDownMenuItem(
                        text = "Quebec",
                        citiesState = citiesState,
                        filterType = FilterType.Quebec
                    )
                    FilterDropDownMenuItem(
                        text = "BC",
                        citiesState = citiesState,
                        filterType = FilterType.BC
                    )
                    FilterDropDownMenuItem(
                        text = "Alberta",
                        citiesState = citiesState,
                        filterType = FilterType.Alberta
                    )
                }
            }
        )
    } else {
        TopAppBar(
            title = { Text(text = stringResource(id = R.string.canadian_cities)) },
            colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ),
            navigationIcon = {
                IconButton(onClick = navigateUp) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
                }
            },
        )
    }
}

@Composable
fun CitiesApp(
    citiesViewModel: CitiesViewModel,
    navController: NavHostController = rememberNavController()
) {

    citiesViewModel.getAllCities()
    val savedCitiesList: List<City> by citiesViewModel.cityList.observeAsState(initial = listOf())

    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentScreen = Screen.valueOf(
        backStackEntry?.destination?.route ?: Screen.Home.name
    )

    val citiesState: MutableStateFlow<Cities> =
        MutableStateFlow(Cities(citiesList))

    val cities by citiesState.collectAsState()

    Scaffold(
        topBar = {
            CitiesAppBar(citiesState, currentScreen, navigateUp = { navController.navigateUp() })
        }) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = Screen.Home.name) {
                HomeScreen(
                    citiesViewModel = citiesViewModel,
                    cities = savedCitiesList,
                    onNextButtonClicked = {
                        navController.navigate(Screen.Cities.name)
                    },
                    navController,
                )
            }
            composable(route = Screen.Cities.name) {

                CitiesScreen(
                    citiesViewModel = citiesViewModel,
                    cities = cities,
                    savedCities = savedCitiesList,
                    onNextButtonClicked = {
                        navController.navigate(Screen.Home.name)
                    },
                    navController,
                )
            }
            composable(route = Screen.City.name) {
                CityScreen(citiesViewModel = citiesViewModel)
            }
        }
    }
}

@Composable
fun FilterDropDownMenuItem(
    text: String,
    citiesState: MutableStateFlow<Cities>,
    filterType: FilterType
) {
    DropdownMenuItem(
        text = { Text(text = text) },
        onClick = {
            citiesState.update {
                Cities(
                    FilterHelper().filterProvinces(
                        filterType,
                        citiesList
                    )
                )
            }
        }
    )
}


