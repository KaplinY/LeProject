package com.littlelemon.leproject.compose.citycard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.littlelemon.leproject.data.CityItem
import com.littlelemon.leproject.data.SavedCitiesList
import com.littlelemon.leproject.viewmodels.CitiesViewModel
import com.littlelemon.leproject.R

@Composable
fun CityCard(cityItem: CityItem){
    ElevatedCard (
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp),
        modifier = Modifier
            .size(width = 180.dp, height = 150.dp),
    ){
        Image(
            painter = painterResource(id = cityItem.image),
            contentDescription = null,
            modifier = Modifier.size(40.dp))
        Text(text = cityItem.name)
        Text(text = cityItem.province)
        if (SavedCitiesList.savedcitiesList.contains(cityItem)) {
            IconButton(
            onClick = { removeCityFromSaved(cityItem) },
            modifier = Modifier
                .clip(shape = CircleShape)
                .size(40.dp)
            ) {
                Icon(painter = painterResource(id = R.drawable.trashbin) , contentDescription = null)
            }
        } else {
            IconButton(
                onClick = { addCityToSaved(cityItem) },
                modifier = Modifier
                    .clip(shape = CircleShape)
                    .size(40.dp)
            ) {
                Icon(painter = painterResource(id = R.drawable.plus), contentDescription = null)
            }
        }
    }
}

fun addCityToSaved(cityItem: CityItem){
    SavedCitiesList.savedcitiesList.add(cityItem)
}
fun removeCityFromSaved(cityItem: CityItem){
    SavedCitiesList.savedcitiesList.remove(cityItem)
}
