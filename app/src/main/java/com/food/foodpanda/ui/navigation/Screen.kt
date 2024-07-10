package com.food.foodpanda.ui.navigation

sealed class Screen(val route: String){
    data object MainScreen : Screen(route = "main_screen")
    data object CuisineScreen : Screen(route = "cuisine_screen")
    data object RestaurantScreen : Screen(route = "restaurant_screen")
}
