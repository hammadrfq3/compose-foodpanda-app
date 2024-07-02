package com.food.foodpanda.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.food.foodpanda.R
import com.food.foodpanda.ui.screens.MainScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun Navigation(navHostController: NavHostController) {

    val systemUiController = rememberSystemUiController()

    NavHost(
        navController = navHostController,
        startDestination = Screen.MainScreen.route,
    ) {
        composable(Screen.MainScreen.route) {
           // MainScreen()
        }
    }

    systemUiController.setStatusBarColor(color = colorResource(id = R.color.foodpanda))
    //systemUiController.setNavigationBarColor(color = colorResource(id = R.color.white))


}