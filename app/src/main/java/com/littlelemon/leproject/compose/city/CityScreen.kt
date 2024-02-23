package com.littlelemon.leproject.compose.city

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.littlelemon.leproject.viewmodels.CitiesViewModel

@Composable
fun CityScreen(citiesViewModel: CitiesViewModel) {
    CityDetails(citiesViewModel)
}

@Composable
fun CityDetails(citiesViewModel: CitiesViewModel) {

    val cityUiState by citiesViewModel.uiState.collectAsState()
    val city = cityUiState.currentCity

    Column {
        Text(
            text = city.city,
            fontSize = 20.sp,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(start = 8.dp, top = 8.dp)
        )
        Text(
            text = city.province,
            fontSize = 16.sp,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(start = 8.dp)
        )
        Image(
            painter = painterResource(id = city.picture),
            contentDescription = null,
            modifier = Modifier
                .padding(8.dp)
                .width(370.dp)
                .height(250.dp)
        )
        Text(
            text = stringResource(id = city.description),
            fontSize = 16.sp,
            textAlign = TextAlign.Left,
            modifier = Modifier.padding(8.dp)
        )
    }
}
