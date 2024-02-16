package com.littlelemon.leproject.compose.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.littlelemon.leproject.R
import com.littlelemon.leproject.compose.citycard.CityCard
import com.littlelemon.leproject.data.City
import com.littlelemon.leproject.viewmodels.CitiesViewModel

@Composable
fun SavedCitiesScreen(
    citiesViewModel: CitiesViewModel,
    cities: List<City>,
    onNextButtonClicked: () -> Unit = {},
    navController: NavController,
){
    Column {
        OutlinedButton(
            onClick = onNextButtonClicked,
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = stringResource(id = R.string.all_cities),
                modifier = Modifier.padding(end = 4.dp))
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
        ) {
            items( items = cities) { city ->
                CityCard(citiesViewModel, city, cities, navController = navController)
            }
        }
    }
}