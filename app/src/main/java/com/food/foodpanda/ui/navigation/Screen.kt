package com.food.foodpanda.ui.navigation

sealed class Screen(val route: String){
    object MainScreen : Screen(route = "main_screen")
}
