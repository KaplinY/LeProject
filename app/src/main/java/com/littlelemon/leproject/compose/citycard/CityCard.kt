package com.littlelemon.leproject.compose.citycard

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.littlelemon.leproject.R
import com.littlelemon.leproject.compose.Screen
import com.littlelemon.leproject.data.City
import com.littlelemon.leproject.viewmodels.CitiesViewModel
import kotlin.math.roundToInt

enum class DragAnchors {
    Start,
    End,
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CityCard(
    citiesViewModel: CitiesViewModel,
    city: City,
    cities: List<City>,
    navController: NavController,
) {
    val density = LocalDensity.current

    val state = remember {
        AnchoredDraggableState(
            initialValue = DragAnchors.Start,
            positionalThreshold = { distance: Float -> distance * 0.5f },
            velocityThreshold = { with(density) { 100.dp.toPx() } },
            animationSpec = tween(),
        ).apply {
            updateAnchors(
                DraggableAnchors {
                    DragAnchors.Start at 0f
                    DragAnchors.End at 200f
                }
            )
        }
    }
    Box {
        if (cities.contains(city)) {
            IconButton(
                onClick = {
                    removeCityFromSaved(citiesViewModel, city)
                    getAllCities(citiesViewModel)
                },
                modifier = Modifier
                    .background(color = Color.Transparent)
                    .size(50.dp)
                    .align(Alignment.CenterStart)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.trashbin),
                    contentDescription = null,
                    modifier = Modifier.size(60.dp)
                )
            }
        } else {
            IconButton(
                onClick = {
                    addCityToSaved(citiesViewModel, city)
                    getAllCities(citiesViewModel)
                },
                modifier = Modifier
                    .background(color = Color.Transparent)
                    .size(50.dp)
                    .align(Alignment.CenterStart)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.plus),
                    contentDescription = null,
                    modifier = Modifier.size(60.dp)
                )
            }
        }

        ElevatedCard(
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            modifier = Modifier
                .size(width = 180.dp, height = 150.dp)
                .offset {
                    IntOffset(
                        x = state
                            .requireOffset()
                            .roundToInt(),
                        y = 0,
                    )
                }
                .anchoredDraggable(state, Orientation.Horizontal),
        ) {
            Column(modifier = Modifier.padding(start = 5.dp)) {
                Image(
                    painter = painterResource(id = city.picture),
                    contentDescription = null,
                    modifier = Modifier
                        .width(140.dp)
                        .clickable {
                            citiesViewModel.updateCurrentCity(city)
                            navController.navigate(
                                Screen.City.name
                            )
                        }
                        .height(120.dp)
                        .clip(RoundedCornerShape(10.dp))
                )
                Row(
                    modifier = Modifier.padding(bottom = 5.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AutoResizedText(text = city.city, fontWeight = FontWeight(600))
                        AutoResizedText(text = city.province, fontWeight = FontWeight(400))
                    }
                }
            }
        }
    }
}


fun addCityToSaved(
    citiesViewModel: CitiesViewModel,
    city: City
) {
    citiesViewModel.addCity(city)

}

fun removeCityFromSaved(
    citiesViewModel: CitiesViewModel,
    city: City
) {
    citiesViewModel.deleteCity(city)

}

fun getAllCities(
    citiesViewModel: CitiesViewModel
) {
    citiesViewModel.getAllCities()
}

