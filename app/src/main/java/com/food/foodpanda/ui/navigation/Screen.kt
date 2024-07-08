package com.food.foodpanda.ui.navigation

sealed class Screen(val route: String){
    object MainScreen : Screen(route = "main_screen")
    object CuisineScreen : Screen(route = "cuisine_screen")
}
