package com.food.foodpanda.ui.screens

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.food.foodpanda.R
import com.food.foodpanda.data.model.MenuItem
import com.food.foodpanda.data.model.RestaurantMenu
import com.food.foodpanda.data.model.RestuarantItem
import com.food.foodpanda.ui.theme.FoodpandaTheme
import com.food.foodpanda.ui.viewmodel.RestaurantScreenViewModel
import kotlinx.coroutines.launch

@Composable
fun RestaurantScreen(
    restaurantItem: RestuarantItem,
    //onBackPress: () -> Unit,
    viewModel: RestaurantScreenViewModel = viewModel()
) {

    Log.e("TAG", "RestaurantScreen")
    val menuData = viewModel.getRestaurantMenu()
    // val scrollState = rememberScrollState()
    var scrollToIndex by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.white))
            .padding(vertical = 16.dp),
    ) {
        RestaurantTopBar {
            //onBackPress.invoke()
        }

        val listState = rememberLazyListState()
        // Remember a CoroutineScope to be able to launch
        val coroutineScope = rememberCoroutineScope()
        //  var scrollToPosition by remember { mutableStateOf(0F) }
        var counters = 5
        val daata = menuData.second

        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            state = listState
        ) {
            item { RestaurantHeader(restaurantItem) }
            item { RestaurantDeals(restaurantItem) }
            item {
                TabScreen(menuData.first) { category ->
                    // Find the position of the first item in the selected category
                    val categoryIndex = menuData.first.indexOfFirst { it.title == category }
                    if (categoryIndex != -1) {
                        val firstItemIndex = menuData.second.indexOfFirst { it.title == menuData.first[categoryIndex].title }
                        if (firstItemIndex != -1) {
                            scrollToIndex = firstItemIndex
                            coroutineScope.launch {
                                listState.animateScrollToItem(scrollToIndex)
                            }
                        }
                    }
                }
            }
            item { MenuItems() }
            item { MenuCards(menuData.first) }


            /* items(
                 count = daata.size,
             ) {
                 Column(
                     modifier = Modifier
                         .padding(vertical = 10.dp)
                         .fillMaxWidth()
                         .height(10.dp)
                         .background(color = colorResource(id = R.color.bg))
                 ) {

                 }
                 Column(
                     modifier = Modifier
                         .padding(horizontal = 16.dp)
                 ) {
                     Text(
                         text = daata[it].title,
                         fontSize = 24.sp,
                         fontWeight = FontWeight.Bold,
                         color = colorResource(id = R.color.black),
                         modifier = Modifier
                             .padding(top = 15.dp, bottom = 15.dp),
                     )
                 }
                 MenuHorizontalItem(menuItem = daata[it], it)
                 counters += 1
             }*/
            menuData.first.forEachIndexed { index, restaurantMenu ->
                Log.e("TAG", "forEachIndexed: $counters")
                counters += 1
                item(key = "mkm") {
                    Column(
                        modifier = Modifier
                            .padding(vertical = 10.dp)
                            .fillMaxWidth()
                            .height(10.dp)
                            .background(color = colorResource(id = R.color.bg))
                    ) {

                    }
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                    ) {
                        Text(
                            text = restaurantMenu.title,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = colorResource(id = R.color.black),
                            modifier = Modifier
                                .padding(top = 15.dp, bottom = 15.dp),
                        )
                    }
                }
                MenuHorizontal(menuItems = restaurantMenu.items)
            }


        }

    }

}

@Composable
fun RestaurantTopBar(
    onBackPress: () -> Unit,
) {

    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.arrow_back),
            contentDescription = "Contact profile picture",
            modifier = Modifier
                .clickable(
                    indication = rememberRipple(
                        bounded = false,
                        color = colorResource(id = R.color.black)
                    ),
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    onBackPress.invoke()
                }
        )
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(id = R.drawable.ic_heart_unfilled_drawer),
            contentDescription = "Contact profile picture",
            modifier = Modifier
                .clickable(
                    indication = rememberRipple(
                        bounded = false,
                        color = colorResource(id = R.color.black)
                    ),
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                }
                .height(22.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.share),
            contentDescription = "Contact profile picture",
            modifier = Modifier
                .clickable(
                    indication = rememberRipple(
                        bounded = false,
                        color = colorResource(id = R.color.black)
                    ),
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                }
                .padding(horizontal = 16.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.search_pink),
            contentDescription = "Contact profile picture",
            modifier = Modifier
                .clickable(
                    indication = rememberRipple(
                        bounded = false,
                        color = colorResource(id = R.color.black)
                    ),
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                }
                .height(22.dp)
        )
    }

}


@Composable
fun RestaurantHeader(
    restaurantItem: RestuarantItem
){

    Row(
        modifier = Modifier
            .padding(top = 24.dp, start = 16.dp, end = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(
        ) {
            Image(
                painter = painterResource(restaurantItem.image),
                contentDescription = "Contact profile picture",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clickable(
                        indication = rememberRipple(
                            bounded = false,
                            color = colorResource(id = R.color.black)
                        ),
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                    }
                    .size(60.dp)
            )
        }


        Text(
            text = restaurantItem.title,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.black),
            fontSize = 18.sp,
            modifier = Modifier
                .padding(start = 10.dp)
            // .fillMaxWidth(.1f)
        )

    }


    Row(
        modifier = Modifier
            .padding(top = 16.dp, start = 16.dp, end = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = restaurantItem.deliveryPrice + " delivery | ${restaurantItem.deliveryTime}",
            color = colorResource(id = R.color.light_text),
            fontSize = 12.sp,
            modifier = Modifier
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "More info",
            fontWeight = FontWeight.SemiBold,
            color = colorResource(id = R.color.foodpanda),
            fontSize = 14.sp,
            modifier = Modifier
        )
    }
    Text(
        text = restaurantItem.minimumPricerOrder,
        color = colorResource(id = R.color.light_text),
        fontSize = 12.sp,
        modifier = Modifier
            .padding(horizontal = 16.dp),
        lineHeight = 3.sp
    )
    Row(
        modifier = Modifier
            .padding(top = 16.dp, start = 16.dp, end = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.star_outline),
            contentDescription = "Contact profile picture",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clickable(
                    indication = rememberRipple(
                        bounded = false,
                        color = colorResource(id = R.color.black)
                    ),
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                }
                .size(19.dp)
        )
        Text(
            text = "${restaurantItem.rating}",
            fontWeight = FontWeight.SemiBold,
            color = colorResource(id = R.color.black),
            fontSize = 14.sp,
            modifier = Modifier
                .padding(start = 10.dp)
        )
        Text(
            text = "4000+ ratings",
            color = colorResource(id = R.color.light_text),
            fontSize = 12.sp,
            modifier = Modifier
                .padding(start = 10.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "See reviews",
            fontWeight = FontWeight.SemiBold,
            color = colorResource(id = R.color.foodpanda),
            fontSize = 14.sp,
            modifier = Modifier
        )
    }
    Row(
        modifier = Modifier
            .padding(top = 16.dp, start = 16.dp, end = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.time),
            contentDescription = "Contact profile picture",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clickable(
                    indication = rememberRipple(
                        bounded = false,
                        color = colorResource(id = R.color.black)
                    ),
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                }
                .size(20.dp)
        )
        Text(
            text = "Delivery: ${restaurantItem.deliveryTime}",
            fontWeight = FontWeight.SemiBold,
            color = colorResource(id = R.color.black),
            fontSize = 14.sp,
            modifier = Modifier
                .padding(start = 10.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "Change",
            fontWeight = FontWeight.SemiBold,
            color = colorResource(id = R.color.light_text),
            fontSize = 12.sp,
            modifier = Modifier,
        )
    }
}

@Composable
fun RestaurantDeals(
    restaurantItem: RestuarantItem
) {

    Row(
        modifier = Modifier
            .padding(top = 16.dp, start = 16.dp, end = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.tags_sale),
            contentDescription = "Contact profile picture",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(22.dp)
        )
        Text(
            text = "Available deals",
            fontWeight = FontWeight.SemiBold,
            color = colorResource(id = R.color.black),
            fontSize = 14.sp,
            modifier = Modifier
                .padding(start = 10.dp)
        )
    }

    Row(
        modifier = Modifier
            .padding(top = 8.dp, start = 16.dp, end = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        DealCards(
            modifier = Modifier
                .fillMaxWidth(.5f)
                .background(
                    color = colorResource(id = R.color.card_light),
                    shape = RoundedCornerShape(10.dp)
                ),
            description = "${restaurantItem.minimumPricerOrder} and special savings for pandapro members."
        )
        DealCards(
            modifier = Modifier
                .background(
                    color = colorResource(id = R.color.foodpanda_light),
                    shape = RoundedCornerShape(10.dp)
                ),
            isFirstCard = false,
            color = R.color.foodpanda,
            description = "${restaurantItem.minimumPricerOrder}. Valid for selected items. Auto applied."
        )
    }

}

@Composable
fun DealCards(
    modifier: Modifier = Modifier,
    isFirstCard: Boolean = true,
    description: String,
    @DrawableRes color: Int = R.color.black
) {

    Column(
        modifier = modifier
            .height(100.dp)
            .padding(start = 10.dp, top = 10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (isFirstCard)
                Row(
                    modifier = Modifier
                        .background(
                            color = colorResource(id = R.color.purple),
                            shape = RoundedCornerShape(20.dp)
                        )
                        .padding(horizontal = 5.dp, vertical = 0.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.crown_white),
                        contentDescription = "Contact profile picture",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(12.dp)
                    )
                    Text(
                        text = "Pro",
                        fontWeight = FontWeight.Medium,
                        color = colorResource(id = R.color.white),
                        fontSize = 10.sp,
                        lineHeight = 20.sp,
                        modifier = Modifier
                            .padding(start = 5.dp),
                    )
                }
            else
                Image(
                    painter = painterResource(id = R.drawable.sale_),
                    contentDescription = "Contact profile picture",
                    modifier = Modifier
                )
            Text(
                text = "15% off",
                fontWeight = FontWeight.Bold,
                color = colorResource(id = color),
                fontSize = 12.sp,
                modifier = Modifier
                    .padding(start = 5.dp),
            )
        }
        Text(
            text = description,
            color = colorResource(id = R.color.black),
            fontSize = 10.sp,
            lineHeight = 14.sp,
            modifier = Modifier
                .padding(top = 5.dp),
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.transparent_crown),
                contentDescription = "Contact profile picture",
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .size(60.dp)
            )
        }
    }

}

@Composable
fun TabScreen(
    data: ArrayList<RestaurantMenu>,
    onTabClicked: (String) -> Unit,
) {
    var tabIndex by remember { mutableIntStateOf(0) }

    //val tabs = listOf("Popular", "Sehri Menu", "Special Nashta", "Mains", "Tandoor", "Paratha", "Sides")

    Column(
        modifier = Modifier
            .padding(top = 20.dp)
            .fillMaxWidth()
    ) {
        ScrollableTabRow(
            selectedTabIndex = tabIndex,
            containerColor = Color.Transparent,
            edgePadding = 16.dp,
            indicator = { tabPositions ->
                Box(
                    modifier = Modifier
                        .wrapContentWidth()
                        .tabIndicatorOffset(tabPositions[tabIndex])
                        .height(4.dp)
                        .background(color = colorResource(id = R.color.foodpanda), shape = RoundedCornerShape(10.dp))
                )
            }
        ) {
            data.forEachIndexed { index, title ->
                Tab(
                    text = {
                        Text(title.title)
                    },
                    selected = tabIndex == index,
                    onClick = {
                        tabIndex = index
                        onTabClicked.invoke(title.title)
                    },
                )
            }
        }
        when (tabIndex) {
            /* 0 -> {
                 MenuItems()
                 MenuCards(data[0].items)
             }
             1 -> MenuCards(data[1].items)*/
            //2 -> MenuCards(data[2].items)
        }
    }
}


@Composable
fun MenuItems() {

    Row(
        modifier = Modifier
            .padding(top = 30.dp, start = 16.dp, end = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.popular),
            contentDescription = "Contact profile picture",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(20.dp)
        )
        Text(
            text = "Popular",
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.black),
            fontSize = 20.sp,
            modifier = Modifier
                .padding(start = 10.dp),
        )
    }
    Text(
        text = "Most ordered right now.",
        color = colorResource(id = R.color.light_text),
        fontSize = 12.sp,
        modifier = Modifier
            .padding(top = 8.dp, start = 16.dp, end = 16.dp),
    )

}

@Composable
fun MenuCards(
    data: ArrayList<RestaurantMenu>
) {


    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        userScrollEnabled = false,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        content = {
            items(data[0].items) {
                MenuCardItem(restaurantMenu = it)
            }
        },
        modifier = Modifier
            .padding(top = 10.dp, start = 16.dp, end = 16.dp)
            .heightIn(max = 1000.dp)
    )


}

@Composable
fun MenuCardItem(
    modifier: Modifier = Modifier,
    restaurantMenu: MenuItem
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
        ) {
            Card(
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
            ) {
                Image(
                    painter = painterResource(id = restaurantMenu.image),
                    contentDescription = "Contact profile picture",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clickable(
                            indication = rememberRipple(
                                bounded = false,
                                color = colorResource(id = R.color.black)
                            ),
                            interactionSource = remember { MutableInteractionSource() }
                        ) {
                        }
                        .height(180.dp)
                )
            }
            Image(
                painter = painterResource(id = R.drawable.add_circle),
                contentDescription = "Contact profile picture",
                modifier = Modifier
                    .padding(bottom = 5.dp, end = 5.dp)
                    .shadow(elevation = 2.dp, shape = CircleShape)
                    .align(Alignment.BottomEnd)
                    .size(30.dp)
                    .clickable(
                        indication = rememberRipple(
                            bounded = false,
                            color = colorResource(id = R.color.black)
                        ),
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                    }
            )
        }
        Text(
            text = restaurantMenu.title,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            lineHeight = 1.sp,
            color = colorResource(id = R.color.black),
            modifier = Modifier
                .padding(top = 8.dp),
        )
        Text(
            text = restaurantMenu.price,
            fontSize = 14.sp,
            lineHeight = 1.sp,
            fontWeight = FontWeight.Medium,
            color = colorResource(id = R.color.light_text),
            modifier = Modifier
                .padding(top = 8.dp),
        )
    }

}


fun LazyListScope.MenuHorizontal(
    menuItems: ArrayList<MenuItem>,
) {

    items(
        count = menuItems.size,
    ) {
        MenuHorizontalItem(menuItem = menuItems[it], it)
    }

}

@Composable
fun MenuHorizontalItem(menuItem: MenuItem, position: Int) {

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(horizontal = 16.dp)
    ) {
        if (position > 0) {
            Column(
                modifier = Modifier
                    .padding(bottom = 15.dp, top = 10.dp)
                    .fillMaxWidth()
                    .height(1.3.dp)
                    .background(color = colorResource(id = R.color.divider))
            ) {

            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column {
                Text(
                    text = menuItem.title,
                    fontSize = 14.sp,
                    lineHeight = 1.sp,
                    fontWeight = FontWeight.Medium,
                    color = colorResource(id = R.color.black),
                    modifier = Modifier
                        .padding(top = 8.dp),
                )
                Text(
                    text = menuItem.price,
                    fontSize = 13.sp,
                    lineHeight = 1.sp,
                    color = colorResource(id = R.color.black),
                    modifier = Modifier
                        .padding(top = 8.dp),
                )
            }
            Card(
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                modifier = Modifier
                    .align(Alignment.CenterEnd)
            ) {
                Image(
                    painter = painterResource(id = menuItem.image),
                    contentDescription = "Contact profile picture",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(120.dp)
                )
            }
            Image(
                painter = painterResource(id = R.drawable.add_circle),
                contentDescription = "Contact profile picture",
                modifier = Modifier
                    .padding(bottom = 5.dp, end = 5.dp)
                    .shadow(elevation = 2.dp, shape = CircleShape)
                    .align(Alignment.BottomEnd)
            )

        }
    }


}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FoodpandaTheme {
        //Greeting("Android")
        //Navigation()
        //NavigationDrawer()
        val item1 =
            RestuarantItem(
                title = "Dogar Restaurant & Biryani",
                offer = "Up to 20% off",
                image = R.drawable.food_1,
                rating = "4.6",
                reviews = "500+",
                minimumPricerOrder = "PKR 249 minimum",
                cuisine = "Pakistani",
                deliveryTime = "15-30 min",
                deliveryPrice = "69 PKR"
            )
        RestaurantScreen(restaurantItem = item1)
    }
}
