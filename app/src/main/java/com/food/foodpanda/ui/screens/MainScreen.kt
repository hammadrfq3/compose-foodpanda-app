package com.food.foodpanda.ui.screens

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.food.foodpanda.R
import com.food.foodpanda.data.model.CardItem
import com.food.foodpanda.data.model.RestuarantItem
import com.food.foodpanda.ui.navigation.Screen
import com.food.foodpanda.ui.viewmodel.MainScreenViewModel
import com.google.gson.Gson

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(
    navigateToCuisine: (route: String) -> Unit,
    navigateToRestaurant: (route: String) -> Unit,
    onclick: () -> Unit,
    viewModel: MainScreenViewModel = viewModel()
) {
    Log.e("TAG", "MainScreen")


    val scrollState = rememberScrollState()

    val upperBoxData = viewModel.getUpperBoxData()
    val restaurantsData = viewModel.getRestaurantsData()
    val cuisineData = viewModel.getCuisineData()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.bg)),
    ) {
        TopAddressBar(onclick)
        CompositionLocalProvider(
            LocalOverscrollConfiguration provides null
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(state = scrollState)
            ) {
                TopSearchBar()
                UpperBox(upperBoxData)
                WhiteArea(restaurantsData, cuisineData,navigateToCuisine,navigateToRestaurant)
            }
        }

    }

}

@Composable
fun TopAddressBar(
    onclick: () -> Unit,
) {

    Row(
        modifier = Modifier
            .background(
                color = colorResource(id = R.color.foodpanda)
            )
            .padding(start = 16.dp, end = 16.dp, top = 12.dp)
        ,
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.ic_menu),
            contentDescription = "Contact profile picture",
            modifier = Modifier
                .clickable(
                    indication = rememberRipple(
                        bounded = false,
                        color = colorResource(id = R.color.black)
                    ),
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    onclick.invoke()
                }
                .padding(4.dp)
                .size(22.dp)
        )
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "Home",
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.white),
                fontSize = 18.sp,
            )
            Text(
                text = "House# 56, Eden Avenue, Washington DC, America",
                fontWeight = FontWeight.Normal,
                color = colorResource(id = R.color.white),
                fontSize = 13.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                lineHeight = 3.sp
            )
        }
        Image(
            painter = painterResource(R.drawable.ic_heart_unfilled),
            contentDescription = "Contact profile picture",
            modifier = Modifier
                .size(22.dp)
        )
        Image(
            painter = painterResource(R.drawable.shopping_bag),
            contentDescription = "Contact profile picture",
            modifier = Modifier
                .size(22.dp)
        )
    }

}

@Composable
fun TopSearchBar() {

    var text by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            //.padding(horizontal = 16.dp)
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.foodpanda))
            .padding(start = 16.dp, end = 10.dp, top = 10.dp, bottom = 14.dp),
    ) {

        BasicTextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    shape = RoundedCornerShape(50.dp),
                    color = Color.White
                )
                .height(40.dp),
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Start),
            singleLine = false,
            interactionSource = remember { MutableInteractionSource() },
        ) { innerTextField ->
            Row(
                Modifier
                    .padding(start = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    Icons.Filled.Search, "contentDescription"
                        )
                Box(
                    Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp)
                ) {
                    innerTextField()
                    if (text.isEmpty()) Text(
                        "Search for shops and restuarants",
                        style = LocalTextStyle.current.copy(
                            color = colorResource(id = R.color.light_text),
                            fontSize = 12.sp
                        )
                    )
                }
            }
        }
    }


}

@Composable
fun UpperBox(
    list: ArrayList<CardItem>
){

    var counter = 0

    Column(
        modifier = Modifier
            .wrapContentSize()
            .padding(horizontal = 14.dp, vertical = 14.dp),
    ) {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            verticalItemSpacing = 10.dp,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            content = {
                items(list) { item ->
                    when (counter) {
                        0 -> CardItem(
                            modifier = Modifier.height(244.dp),
                            cardItem = item,
                        )
                        1 -> CardItem(
                            modifier = Modifier.height(160.dp),
                            cardItem = item,
                            imageModifier = Modifier
                                .width(120.dp)
                                .height(70.dp)
                        )
                        2 -> CardItem(
//                            modifier = Modifier.height(100.dp),
                            cardItem = item,
                            imageModifier = Modifier
                                .width(40.dp)
                                .height(40.dp),
                            descModifier = Modifier.fillMaxWidth(.6f)
                        )
                        3 -> CardItem(
                           // modifier = Modifier.height(100.dp),
                            cardItem = item,
                            imageModifier = Modifier
                                .width(60.dp)
                                .height(40.dp),
                            descModifier = Modifier.fillMaxWidth(.6f)
                        )
                        else -> CardItem(
                           // modifier = Modifier.height(100.dp),
                            cardItem = item,
                            imageModifier = Modifier
                                .width(60.dp)
                                .height(40.dp),
                            descModifier = Modifier.fillMaxWidth(.6f)
                        )
                    }
                    counter++
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(330.dp)
        )
    }


}

@Composable
fun CardItem(
    modifier: Modifier = Modifier,
    imageModifier: Modifier = Modifier,
    descModifier: Modifier = Modifier,
    cardItem: CardItem,
){

    Card(
        colors = CardColors(
            contentColor = Color.Transparent,
            containerColor = Color.White,
            disabledContentColor = Color.Transparent,
            disabledContainerColor = Color.Transparent
        ),
        shape = RoundedCornerShape(10.dp),
        //backgroundColor = Color.White,
        modifier = Modifier
            .fillMaxWidth()
            //.padding(10.dp)
            .wrapContentHeight()
    ) {
        Box(
            modifier = modifier
                /* .background(
                    shape = RoundedCornerShape(10.dp),
                    color = colorResource(id = R.color.white)
                )*/
                .fillMaxWidth()
                .wrapContentHeight()
                //.padding(10.dp)
                .clickable(
                    /*indication = rememberRipple(
                        bounded = false,
                        radius = 10.dp,
                        color = colorResource(id = R.color.light_text_ripple)
                    ),
                    interactionSource = remember { MutableInteractionSource() }*/
                ) {
                    //navController.popBackStack()
                }
                .padding(10.dp)
        ) {
            Column(
                modifier = modifier
                    .align(Alignment.TopStart)
            ) {
                Text(
                    text = cardItem.title,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.black),
                    fontSize = 16.sp
                )
                Text(
                    text = cardItem.desc,
                    fontWeight = FontWeight.Normal,
                    color = colorResource(id = R.color.black),
                    fontSize = 13.sp,
                    modifier = descModifier,
                    lineHeight = 14.sp
                )
            }
            // Spacer(modifier = Modifier.align(Alignment.Center))
            Image(
                painter = painterResource(id = cardItem.image),
                contentDescription = "Contact profile picture",
                modifier = imageModifier
                    .align(Alignment.BottomEnd)
                //.background(color = Color.Blue)
            )
        }
    }


}

@Composable
fun WhiteArea(
    restaurantsData: ArrayList<RestuarantItem>,
    cardItemData: ArrayList<CardItem>,
    navigateToCuisine: (route: String) -> Unit,
    navigateToRestaurant: (route: String) -> Unit,
) {

    Column(
        modifier = Modifier
            .padding(top = 3.dp)
            .fillMaxSize()
            .background(color = Color.White)
            .padding(15.dp)
    ) {
        AdCard()
        Text(
            text = "Order it again",
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.black),
            fontSize = 18.sp,
            modifier = Modifier
                .padding(top = 20.dp)
        )
        OrderItAgain(restaurantsData, navigateToRestaurant)
        Text(
            text = "Top Brands",
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.black),
            fontSize = 18.sp,
            modifier = Modifier
                .padding(top = 20.dp)
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TopBrands(
                R.drawable.texas_logo,
                "Texas Chicken",
                Modifier
                    .size(100.dp)
                    .padding(10.dp),
                "30 mins",
            ){}
            TopBrands(
                R.drawable.papa_johns,
                "Papa Johns",
                Modifier
                    .size(100.dp)
                    .padding(10.dp),
                "30 mins"
            ){}
            TopBrands(
                R.drawable.baskin_robbins_logo,
                "Baskin Robbins",
                Modifier
                    .size(100.dp)
                    .padding(10.dp),
                "26 mins"
            ){}
        }
        Text(
            text = "Cuisines",
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.black),
            fontSize = 18.sp,
            modifier = Modifier
                .padding(top = 30.dp)
        )
        Cuisines(data = cardItemData,navigateToCuisine)
    }

}

@Composable
fun AdCard(){

    Card(
        colors = CardColors(
            contentColor = Color.White,
            containerColor = Color.Transparent,
            disabledContentColor = Color.Transparent,
            disabledContainerColor = Color.Transparent
        ),
        modifier = Modifier
            .border(1.dp, color = colorResource(id = R.color.light_text2), shape = RoundedCornerShape(10.dp))
    ) {
        Box(
            modifier = Modifier
                /*.background(
                    shape = RoundedCornerShape(10.dp),
                    color = colorResource(id = R.color.purple_200)
                )*/
                .fillMaxWidth()
                .height(190.dp)
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 10.dp, top = 5.dp, bottom = 5.dp)
            ) {
                Text(
                    text = "Sponsored",
                    fontWeight = FontWeight.Normal,
                    color = colorResource(id = R.color.light_text),
                    fontSize = 12.sp
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "Sprite Laga!",
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.black),
                    fontSize = 16.sp,
                )
                Text(
                    text = "Prizes Jeet, Thand Rakh",
                    fontWeight = FontWeight.Normal,
                    color = colorResource(id = R.color.light_text),
                    fontSize = 16.sp,
                )
                Button(
                    onClick = {},
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonColors(
                        containerColor = colorResource(id = R.color.foodpanda),
                        contentColor = Color.White,
                        disabledContentColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent
                    ),
                    modifier = Modifier
                        .padding(top = 10.dp)
                ){
                    Text(
                        text = "Order now",
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.white),
                        fontSize = 16.sp,
                    )
                }
            }
            // Spacer(modifier = Modifier.align(Alignment.Center))
            Image(
                painter = painterResource(id = R.drawable.sprite),
                contentDescription = "Contact profile picture",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                //.background(color = Color.Blue)
            )
        }
    }


}

@Composable
fun OrderItAgain(
    list: ArrayList<RestuarantItem>,
    navigateTo: (route: String) -> Unit,
) {

        LazyHorizontalGrid(
            rows = GridCells.Fixed(1),
            content = {
                items(list) { item ->
                    RestaurantsCardItem(item, navigateTo)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        )

}

@Composable
fun RestaurantsCardItem(
    restaurantItem: RestuarantItem,
    navigateTo: (route: String) -> Unit,
) {

    Column(
        modifier = Modifier
            // .background(color = Color.Red)
            .padding(end = 10.dp)
            .wrapContentSize()
            .clickable(
                indication = rememberRipple(
                    bounded = true,
                    color = colorResource(id = R.color.black)
                ),
                interactionSource = remember { MutableInteractionSource() }
            ) {
                val json = Uri.encode(Gson().toJson(restaurantItem))
                navigateTo.invoke(Screen.RestaurantScreen.route.plus("/${json}"))
            }
    ) {
        Box(
            modifier = Modifier
                //.background(color = Color.Blue)
                .wrapContentSize()

        ) {
            Card(
                modifier = Modifier.align(Alignment.TopStart)
            ) {
                Image(
                    painter = painterResource(id = restaurantItem.image),
                    contentDescription = "Contact profile picture",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(270.dp)
                        .height(150.dp)
                )
            }
            OfferLayout(text = restaurantItem.offer)
        }
        Row(
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
                // .background(color = Color.Red)
                .width(270.dp),
            horizontalArrangement = Arrangement.spacedBy(2.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = restaurantItem.title,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.black),
                fontSize = 14.sp,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                lineHeight = 3.sp,
                modifier = Modifier.fillMaxWidth(.7f)
            )
            Image(
                painter = painterResource(id = R.drawable.star),
                contentDescription = "Contact profile picture",
                modifier = Modifier
                 //   .fillMaxWidth(.1f)
                    .size(14.dp),

            )
            Text(
                text = restaurantItem.rating,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.black),
                fontSize = 12.sp,
                lineHeight = 3.sp,
                modifier = Modifier
                   // .fillMaxWidth(.1f)
            )
            Text(
                text = "(${restaurantItem.reviews})",
                fontWeight = FontWeight.Normal,
                color = colorResource(id = R.color.light_text),
                fontSize = 12.sp,
                lineHeight = 3.sp,
                modifier = Modifier
                   // .fillMaxWidth(.1f)
            )
        }
        Text(
            text = "$$ ${restaurantItem.minimumPricerOrder} - ${restaurantItem.cuisine}",
            fontWeight = FontWeight.Normal,
            color = colorResource(id = R.color.light_text),
            fontSize = 12.sp,
            modifier = Modifier
            // .fillMaxWidth(.1f)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                //.background(color = Color.Blue)
                .width(270.dp),
            horizontalArrangement = Arrangement.spacedBy(2.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.actions_timer),
                contentDescription = "Contact profile picture",
                modifier = Modifier
                    .size(16.dp),
                )
            Text(
                text = restaurantItem.deliveryTime,
                fontWeight = FontWeight.Normal,
                color = colorResource(id = R.color.light_text),
                lineHeight = 3.sp,
                fontSize = 12.sp,
            )
            Image(
                painter = painterResource(id = R.drawable.delivery),
                contentDescription = "Contact profile picture",
                modifier = Modifier
                    .size(16.dp),
            )
            Text(
                text = restaurantItem.deliveryPrice,
                fontWeight = FontWeight.Normal,
                color = colorResource(id = R.color.light_text),
                fontSize = 12.sp,
                lineHeight = 3.sp,
                modifier = Modifier
                // .fillMaxWidth(.1f)
            )
        }
    }

}

@Composable
fun OfferLayout(text: String){

    Text(
        modifier = Modifier
            .padding(top = 10.dp)
            .background(
                color = colorResource(id = R.color.foodpanda),
                shape = RoundedCornerShape(topEnd = 10.dp, bottomEnd = 10.dp)
            )
            .padding(vertical = 0.dp, horizontal = 6.dp),
        text = text,
        color = Color.White,
        fontSize = 10.sp,
        fontWeight = FontWeight.Bold,
    )

}

@Composable
fun TopBrands(
    logo: Int,
    title: String,
    modifier: Modifier,
    distance: String = "",
    contentScale: ContentScale = ContentScale.Fit,
    cardContainerColor: Color = colorResource(id = R.color.bg),
    navigateTo: (route: String) -> Unit,
){


    Column(
        modifier = Modifier
            .padding(top = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier,
            shape = RoundedCornerShape(10.dp),
            colors = CardColors(
                containerColor = cardContainerColor,
                contentColor = Color.Transparent,
                disabledContentColor = Color.Transparent,
                disabledContainerColor = Color.Transparent
            )
        ) {
            Image(
                painter = painterResource(id = logo),
                contentDescription = "Contact profile picture",
                contentScale = contentScale,
                modifier = modifier
                    .clickable {
                        navigateTo(Screen.CuisineScreen.route.plus("/$title"))
                    }
            )
        }
        Text(
            modifier = Modifier
                .padding(top = 5.dp),
            text = title,
            color = Color.Black,
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
        )
        if (distance.isNotEmpty())
            Text(
            modifier = Modifier,
            text = distance,
            color = colorResource(id = R.color.light_text),
            fontSize = 12.sp,
            lineHeight = 2.sp,
            fontWeight = FontWeight.Bold,
        )
    }


}

@Composable
fun Cuisines(
    data: ArrayList<CardItem>,
    navigateTo: (route: String) -> Unit,
) {

    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        content = {
            items(data) { item ->
                TopBrands(
                    item.image,
                    item.title,
                    Modifier.size(80.dp),
                    contentScale = ContentScale.Crop,
                ){
                    navigateTo.invoke(it)
                }
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
    )

}