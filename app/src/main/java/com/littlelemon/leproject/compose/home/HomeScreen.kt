package com.littlelemon.leproject.compose.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.littlelemon.leproject.compose.citycard.CityCard
import com.littlelemon.leproject.data.City
import com.littlelemon.leproject.viewmodels.CitiesViewModel
import com.littlelemon.leproject.R


@Composable
fun HomeScreen(
    citiesViewModel: CitiesViewModel,
    cities: List<City>,
    onNextButtonClicked: () -> Unit = {},
    navController: NavController,
    modifier: Modifier = Modifier
){
    if (cities.isEmpty()) {
        EmptyHomeScreen(citiesViewModel, onNextButtonClicked)
    } else {
        SavedCitiesScreen(citiesViewModel, cities, onNextButtonClicked, navController)
    }
}

@Composable
fun SavedCitiesScreen(
    citiesViewModel: CitiesViewModel,
    cities: List<City>,
    onNextButtonClicked: () -> Unit = {},
    navController: NavController,
    ){

        Column {
            OutlinedButton(onClick = onNextButtonClicked) {
                Text(text = "To All Cities", modifier = Modifier.padding(end = 4.dp))
                Icon(
                    painter = painterResource(id = R.drawable.cityscape),
                    contentDescription = null,
                    modifier = Modifier.size(30.dp))
            }
            LazyVerticalGrid(
                columns = GridCells.Fixed(count = 2),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                ){
                items( items = cities) { city ->
                    CityCard(citiesViewModel, city, cities, navController = navController)
                }
            }
        }
}

@Composable
fun EmptyHomeScreen(
    citiesViewModel: CitiesViewModel,
    onNextButtonClicked: () -> Unit = {},
    ){
    Column() {
        Text(text = "No saved cities")
        OutlinedButton(onClick = onNextButtonClicked) {
            Text(text = "Add new city")
            Icon(imageVector = Icons.Filled.Add, contentDescription = null)
        }
    }
}

