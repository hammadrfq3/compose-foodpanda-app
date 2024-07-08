package com.food.foodpanda.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.food.foodpanda.R
import com.food.foodpanda.data.model.NavigationItems
import com.food.foodpanda.ui.screens.CuisineScreen
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
            title = "Become a pandapro",
            selectedIcon = ImageVector.vectorResource(id = R.drawable.crown),
            unselectedIcon = Icons.Outlined.Home
        ),
        NavigationItems(
            title = "Vouchers",
            selectedIcon = ImageVector.vectorResource(id = R.drawable.voucher),
            unselectedIcon = Icons.Outlined.Settings
        ),
        NavigationItems(
            title = "Favourites",
            selectedIcon = ImageVector.vectorResource(id = R.drawable.ic_heart_unfilled_drawer),
            unselectedIcon = Icons.Outlined.Info
        ),
        NavigationItems(
            title = "Orders & reordering",
            selectedIcon = ImageVector.vectorResource(id = R.drawable.receipt),
            unselectedIcon = Icons.Outlined.Edit,
            badgeCount = 105
        ),
        NavigationItems(
            title = "View profile",
            selectedIcon = ImageVector.vectorResource(id = R.drawable.profile),
            unselectedIcon = Icons.Outlined.Settings
        ),
        NavigationItems(
            title = "Addresses",
            selectedIcon = ImageVector.vectorResource(id = R.drawable.location),
            unselectedIcon = Icons.Outlined.Settings
        ),
        NavigationItems(
            title = "Panda rewards",
            selectedIcon = ImageVector.vectorResource(id = R.drawable.rewards),
            unselectedIcon = Icons.Outlined.Settings
        ),
        NavigationItems(
            title = "Help center",
            selectedIcon = ImageVector.vectorResource(id = R.drawable.help),
            unselectedIcon = Icons.Outlined.Settings
        )
    )

    val navController = rememberNavController()
    val colors = MaterialTheme.colorScheme
    val systemUiController = rememberSystemUiController()
    var statusBarColor by remember { mutableStateOf(colors.primary) }

   // systemUiController.setStatusBarColor(color = colorResource(id = R.color.foodpanda))


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
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier
                            .padding(start = 0.dp)
                            .background(color = colorResource(id = R.color.foodpanda))
                            .fillMaxWidth()
                            .fillMaxHeight(.28f)
                            .padding(start = 10.dp, bottom = 10.dp),
                    ) {
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = "H",
                            modifier = Modifier
                                .padding(bottom = 10.dp)
                                .background(color = Color.White, shape = RoundedCornerShape(50.dp))
                                .padding(horizontal = 12.dp, vertical = 5.dp),
                            fontWeight = FontWeight.Bold,
                            color = colorResource(id = R.color.foodpanda)
                        )
                        Text(
                            text = "Hammad Rafiq",
                            modifier = Modifier,
                            fontWeight = FontWeight.Medium,
                            color = colorResource(id = R.color.white)
                        )
                    }
                    Row(
                        modifier = Modifier
                            .padding(start = 0.dp)
                            .background(color = colorResource(id = R.color.white))
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "PandaPay",
                            modifier = Modifier,
                            fontWeight = FontWeight.Medium,
                            color = colorResource(id = R.color.foodpanda)
                        )
                        Text(
                            text = "Rs. 0.00",
                            modifier = Modifier
                                .background(color = colorResource(id = R.color.foodpanda_light), shape = RoundedCornerShape(50.dp))
                                .padding(vertical = 0.dp, horizontal = 8.dp),
                            color = colorResource(id = R.color.foodpanda),
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    Text(
                        text = "Top up, check your balance and get exciting offers!",
                        modifier = Modifier
                            .background(color = colorResource(id = R.color.white))
                            .padding(start = 10.dp, end = 10.dp, bottom = 12.dp),
                        color = colorResource(id = R.color.black),
                        fontWeight = FontWeight.Normal,
                        fontSize = 13.sp,
                        lineHeight = 18.sp
                    )
                    HorizontalDivider(
                        color = colorResource(id = R.color.light_text_ripple),
                        thickness = 0.dp,
                        modifier = Modifier
                            .background(
                                color = colorResource(id = R.color.light_text_ripple)
                            )
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)
                    )
                    Column(
                        modifier = Modifier
                            .padding(start = 0.dp)
                            .background(color = colorResource(id = R.color.white))
                            .fillMaxSize()
                            .padding(start = 0.dp, bottom = 10.dp),
                    ) {
                        Spacer(modifier = Modifier.height(16.dp)) //space (margin) from top
                        items.forEachIndexed { index, item ->
                            NavigationDrawerItem(
                                label = {
                                    Text(
                                        text = item.title,
                                        fontSize = 13.sp,
                                        modifier = Modifier
                                            .padding(start = 16.dp)
                                    )
                                },
                                shape = RectangleShape,
                                colors = NavigationDrawerItemDefaults
                                    .colors(
                                        selectedContainerColor = Color.White,
                                        unselectedContainerColor = Color.White
                                    ),
                                selected = index == selectedItemIndex,
                                onClick = {
                                    selectedItemIndex = index
                                    scope.launch {
                                        drawerState.close()
                                    }
                                },
                                icon = {
                                    Icon(
                                        /*imageVector = if (index == selectedItemIndex) {
                                            item.selectedIcon
                                        } else
                                            item.unselectedIcon,*/
                                        imageVector = item.selectedIcon,
                                        tint = if (index == 0)
                                            colorResource(id = R.color.panda_pro)
                                        else colorResource(id = R.color.foodpanda),
                                        contentDescription = item.title,
                                        modifier = Modifier.size(20.dp)
                                    )
                                },
                                /*badge = {  // Show Badge
                                    item.badgeCount?.let {
                                        Text(text = item.badgeCount.toString())
                                    }
                                },*/
                                modifier = Modifier
                                    .padding(0.dp) //padding between items
                            )
                        }

                    }
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
                }, navigateTo = {
                    navController.navigate(it)
                })

                LaunchedEffect(Unit) {
                    statusBarColor = colors.primary
                }
            }
            composable(
                Screen.CuisineScreen.route.plus("/{cuisine}"),
                arguments = listOf(navArgument("cuisine") { type = NavType.StringType }
                )) { b ->
                b.arguments?.getString("cuisine").let { cuisine ->
                    if (cuisine != null) {
                        CuisineScreen(
                            cuisine,
                            onBackPress = {
                            navController.navigateUp()
                        })
                    }
                }

                LaunchedEffect(Unit) {
                    statusBarColor = colors.background
                }
            }
        }

        LaunchedEffect(statusBarColor) {
            systemUiController.setStatusBarColor(statusBarColor)
        }

    }

}