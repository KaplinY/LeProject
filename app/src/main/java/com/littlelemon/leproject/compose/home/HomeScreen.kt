package com.littlelemon.leproject.compose.home

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.littlelemon.leproject.R
import com.littlelemon.leproject.compose.citycard.CityCard
import com.littlelemon.leproject.data.Cities
import com.littlelemon.leproject.data.CityItem
import com.littlelemon.leproject.data.SavedCitiesList
import kotlinx.coroutines.launch


@Composable
fun HomeScreen(
    cities: Cities,
    onNextButtonClicked: () -> Unit = {},
    modifier: Modifier = Modifier
){
    val savedCities = SavedCitiesList.savedcitiesList
    if (savedCities.isEmpty()) {
        EmptyHomeScreen(onNextButtonClicked)
    } else {
        SavedCitiesScreen(cities, onNextButtonClicked)
    }
}

@Composable
fun SavedCitiesScreen(
    cities: Cities,
    onNextButtonClicked: () -> Unit = {},
    ){
        Column {
            Button(onClick = onNextButtonClicked) {
                Text(text = "Button")
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

@Composable
fun EmptyHomeScreen(
    onNextButtonClicked: () -> Unit = {},
    ){
    Column() {
        Text(text = "No saved cities")
        Button(onClick = onNextButtonClicked) {
            Text(text = "Add new city")
        }
    }
}

