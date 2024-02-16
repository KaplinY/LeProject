package com.littlelemon.leproject.compose.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.littlelemon.leproject.R

@Composable
fun EmptyHomeScreen(
    onNextButtonClicked: () -> Unit = {},
){
    Image(
        painter = painterResource(id = R.drawable.background),
        contentDescription = null,
        contentScale = ContentScale.FillBounds,
        modifier = Modifier.fillMaxSize(),
        alpha = 0.15f
    )
    Column() {
        OutlinedButton(
            onClick = onNextButtonClicked,
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = stringResource(id = R.string.add_city))
            Icon(imageVector = Icons.Filled.Add, contentDescription = null)
        }
        Row(
            modifier = Modifier
                .padding(top = 150.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.no_saved),
                fontSize = 20.sp,
                fontWeight = FontWeight(600)
            )
        }
    }
}