package com.food.foodpanda.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.food.foodpanda.R
import com.food.foodpanda.ui.screens.MainScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun Navigation() {

    val systemUiController = rememberSystemUiController()

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.MainScreen.route,
    ) {
       /* composable(
            Screen.MainScreen.route.plus("?isReset={isReset}"),
            arguments = listOf(navArgument("isReset") { defaultValue = false }
            )) { b ->
            b.arguments?.getBoolean("isReset").let { isReset ->
                if (isReset != null) {
                    LockScreen(isReset = isReset) {
                        navController.navigate(Screen.MainScreen.route)
                    }
                }
            }
        }*/
        composable(Screen.MainScreen.route) {
            MainScreen(onVaultClick = {
                /*when (it) {
                    "Notes" -> {
                        navController.navigate(Screen.NoteVaultScreen.route)
                    }

                    "Photos" -> {
                        navController.navigate(Screen.VaultScreen.route.plus("/photos"))
                    }

                    "Videos" -> {
                        navController.navigate(Screen.VaultScreen.route.plus("/videos"))
                    }

                    "setting" -> {
                        navController.navigate(Screen.SettingScreen.route)
                    }

                    else -> {
                        navController.navigate(Screen.AudioVaultScreen.route)
                    }
                }*/
            })
        }
        /*composable(Screen.CreateNoteScreen.route) {
            CreateNoteScreen(onClick = {
                when (it) {
                    "Notes" -> {
                        navController.navigate(Screen.CreateNoteScreen.route)
                    }
                }
            })
        }
        composable(
            Screen.AlbumScreen.route.plus("/{type}"),
            arguments = listOf(navArgument("type") { type = NavType.StringType }
            )) { b ->
            b.arguments?.getString("type").let { type ->
                if (type != null) {
                    AlbumScreen(type = type, navController) {
                        //navController.navigate("${Screen.VaultScreen.route}/${it}")
                    }
                }
            }
        }
        composable(Screen.AudioAlbumScreen.route) {
            AudioAlbumScreen(navController) {
                navController.navigate("${Screen.VaultScreen.route}/${it}")
            }
        }
        composable(Screen.SettingScreen.route) {
            SettingScreen(navController) {
                //navController.navigate("${Screen.VaultScreen.route}/${it}")
            }
        }
        composable(Screen.NoteVaultScreen.route) {
            NoteVaultScreen(navController) {
                navController.navigate(Screen.CreateNoteScreen.route)
            }
        }
        composable(Screen.AudioVaultScreen.route) {
            AudioVaultScreen(navController) {
                navController.navigate(Screen.AudioAlbumScreen.route)
            }
        }
        composable(
            Screen.VaultScreen.route.plus("/{type}"),
            arguments = listOf(navArgument("type") { type = NavType.StringType }
            )) { b ->
            b.arguments?.getString("type").let { type ->
                if (type != null) {
                    VaultScreen(type = type, navController) {
                        navController.navigate("${Screen.AlbumScreen.route}/${it}")
                    }
                }
            }
        }*/
    }

    systemUiController.setStatusBarColor(color = colorResource(id = R.color.foodpanda))
    //systemUiController.setNavigationBarColor(color = colorResource(id = R.color.white))


}