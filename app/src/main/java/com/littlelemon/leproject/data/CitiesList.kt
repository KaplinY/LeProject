package com.littlelemon.leproject.data

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.littlelemon.leproject.R

object CitiesList {
    val citiesList = mutableListOf(
        City("Toronto ", "Ontario", R.drawable.toronto, R.string.toronto_d),
        City("Ottawa ", "Ontario", R.drawable.ottawa, R.string.ottawa_d),
        City("Vancouver ", "British Columbia", R.drawable.vancouver, R.string.vancouver_d),
        City("Calgary ", "Alberta", R.drawable.calgary, R.string.calgary_d),
        City("Winnipeg ", "Manitoba", R.drawable.winnipeg, R.string.winnipeg_d),
        City("Quebec City ", "Quebec", R.drawable.quebec, R.string.quebec_d),
        City("Montreal ", "Quebec", R.drawable.montreal, R.string.montreal_d),
        City("Hamilton ", "Ontario", R.drawable.hamilton, R.string.hamilton_d),
        City("Edmonton ", "Alberta", R.drawable.edmonton, R.string.edmonton_d),
        City("Saskatoon ", "Saskatchewan", R.drawable.saskatoon, R.string.saskatoon_d),
        City("Kitchener ", "Ontario", R.drawable.kitchener, R.string.kitchener_d),
        City("Halifax ", "Nova Scotia", R.drawable.halifax, R.string.halifax_d),
        City("Victoria ", "British Columbia", R.drawable.victoria, R.string.victoria_d),
        City("Kelowna ", "British Columbia", R.drawable.kelowna, R.string.kelowna_d),
    )
}
