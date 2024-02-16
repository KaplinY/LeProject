package com.littlelemon.leproject.compose

sealed class Screen(val route: String) {
    object Home: Screen(route = "home_screen")
    object Cities: Screen(route = "cities_screen")
    object City: Screen(route = "city_screen")
}