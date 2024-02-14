package com.littlelemon.leproject.compose.cities

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.littlelemon.leproject.data.Cities
import com.littlelemon.leproject.data.CityItem
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.littlelemon.leproject.compose.citycard.CityCard
import com.littlelemon.leproject.data.City
import com.littlelemon.leproject.viewmodels.CitiesViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CitiesScreen(
    citiesViewModel: CitiesViewModel,
    cities: Cities,
    savedCities: List<City>,
    onNextButtonClicked: () -> Unit = {},
    navController: NavController,
    modifier: Modifier = Modifier
){
    Column {
        OutlinedButton(onClick = onNextButtonClicked) {
            Text(text = "Home")
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