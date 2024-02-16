package com.littlelemon.leproject.compose.citycard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
//import com.littlelemon.leproject.viewmodels.CitiesViewModel
import com.littlelemon.leproject.R
import com.littlelemon.leproject.data.Cities
import com.littlelemon.leproject.data.City
import com.littlelemon.leproject.viewmodels.CitiesViewModel

@Composable
fun CityCard(
    citiesViewModel: CitiesViewModel,
    city: City,
    cities: List<City>,
    navController: NavController,
    ){
    ElevatedCard (
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = Modifier
            .size(width = 180.dp, height = 150.dp),
    ) {
        Column (modifier = Modifier.padding(start = 5.dp)){
            Image(
                painter = painterResource(id = city.picture),
                contentDescription = null,
                modifier = Modifier
                    .width(140.dp)
                    .clickable {
                        citiesViewModel.updateCurrentCity(city)
                        navController.navigate(
                            route = "city_screen"
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
                        .width(140.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AutoResizedText(text = city.city, fontWeight = FontWeight(600))
                    AutoResizedText(text = city.province, fontWeight = FontWeight(400))
                }

                if (cities.contains(city)) {
                    IconButton(
                        onClick = { removeCityFromSaved(citiesViewModel, city) },
                        modifier = Modifier.background(color = Color.Transparent)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.trashbin),
                            contentDescription = null,
                            modifier = Modifier.size(35.dp)
                        )
                    }
                } else {
                    IconButton(
                        onClick = { addCityToSaved(citiesViewModel, city) },
                        modifier = Modifier.background(color = Color.Transparent)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.plus),
                            contentDescription = null,
                            modifier = Modifier.size(35.dp)
                        )
                    }
                }
            }
        }
    }
}

fun addCityToSaved(
    citiesViewModel: CitiesViewModel,
    city: City
){
    citiesViewModel.addCity(city)
}
fun removeCityFromSaved(
    citiesViewModel: CitiesViewModel,
    city: City
){
    citiesViewModel.deleteCity(city)
}

