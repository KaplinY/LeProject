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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ElevatedCard
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import com.littlelemon.leproject.compose.citycard.CityCard

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CitiesScreen(
    cities: Cities,
    onNextButtonClicked: () -> Unit = {},
    modifier: Modifier = Modifier
){
    Column {
        Button(onClick = onNextButtonClicked) {
            Text(text = "Back")
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(count = 2),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            ){items(
                items = cities.items,
                itemContent = {cityItem: CityItem ->
                    CityCard(cityItem)
                }
            )
        }
    }
}