package com.littlelemon.leproject.compose.cities

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.littlelemon.leproject.data.Cities
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.littlelemon.leproject.compose.citycard.CityCard
import com.littlelemon.leproject.data.City
import com.littlelemon.leproject.viewmodels.CitiesViewModel
import com.littlelemon.leproject.R

@Composable
fun CitiesScreen(
    citiesViewModel: CitiesViewModel,
    cities: Cities,
    savedCities: List<City>,
    onNextButtonClicked: () -> Unit = {},
    navController: NavController,
){
    Column {
        OutlinedButton(
            onClick = onNextButtonClicked,
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = stringResource(id = R.string.home))
            Icon(imageVector = Icons.Filled.Home, contentDescription = null)
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(count = 2),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            ){items(
                items = cities.items,
                itemContent = {city: City ->
                    CityCard(citiesViewModel, city, savedCities, navController = navController)
                }
            )
        }
    }
}