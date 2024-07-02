package com.food.foodpanda.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.food.foodpanda.R
import com.food.foodpanda.data.model.NavigationItems
import com.food.foodpanda.ui.screens.MainScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
fun NavigationDrawer() {

    ///List of Navigation Items that will be clicked
    val items = listOf(
        NavigationItems(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home
        ),
        NavigationItems(
            title = "Info",
            selectedIcon = Icons.Filled.Info,
            unselectedIcon = Icons.Outlined.Info
        ),
        NavigationItems(
            title = "Edit",
            selectedIcon = Icons.Filled.Edit,
            unselectedIcon = Icons.Outlined.Edit,
            badgeCount = 105
        ),
        NavigationItems(
            title = "Settings",
            selectedIcon = Icons.Filled.Settings,
            unselectedIcon = Icons.Outlined.Settings
        )
    )

    val navController = rememberNavController()

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(color = colorResource(id = R.color.foodpanda))


    //Remember Clicked index state
    var selectedItemIndex by remember { mutableIntStateOf(0) }
    // var selectedItemIndex by rememberSaveable { mutableStateOf(0) }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier
                    .fillMaxWidth(.78f),
                drawerShape = RectangleShape
            ) {
                Spacer(modifier = Modifier.height(16.dp)) //space (margin) from top
                items.forEachIndexed { index, item ->
                    NavigationDrawerItem(
                        label = { Text(text = item.title) },
                        selected = index == selectedItemIndex,
                        onClick = {
                            selectedItemIndex = index
                            scope.launch {
                                drawerState.close()
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = if (index == selectedItemIndex) {
                                    item.selectedIcon
                                } else item.unselectedIcon,
                                contentDescription = item.title
                            )
                        },
                        badge = {  // Show Badge
                            item.badgeCount?.let {
                                Text(text = item.badgeCount.toString())
                            }
                        },
                        modifier = Modifier
                            .padding(NavigationDrawerItemDefaults.ItemPadding) //padding between items
                    )
                }

            }
        },
        gesturesEnabled = true,
    ) {
        NavHost(
            navController = navController, startDestination = Screen.MainScreen.route
        ) {
            composable(Screen.MainScreen.route) {
                MainScreen(onclick = {
                    scope.launch { drawerState.open() }
                })
            }
        }

    }

}