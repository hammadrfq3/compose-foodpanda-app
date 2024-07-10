package com.food.foodpanda.ui.screens

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.food.foodpanda.R
import com.food.foodpanda.data.model.RestuarantItem
import com.food.foodpanda.ui.navigation.Screen
import com.food.foodpanda.ui.viewmodel.CuisineScreenViewModel

@Composable
fun CuisineScreen(
    cuisine: String,
    onBackPress: () -> Unit,
    viewModel: CuisineScreenViewModel = viewModel()
) {
    Log.e("TAG", "CuisineScreen")


    val restaurantsData = viewModel.getRestaurantsData()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.white))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        CuisineTopBar(cuisine) {
            onBackPress.invoke()
        }
        HorizontalFilterArea()
        RestaurantArea(restaurantsData)
    }

}

@Composable
fun CuisineTopBar(
    cuisine: String,
    navigateTo: (route: String) -> Unit,
) {

    Row(
        modifier = Modifier,
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
                    navigateTo(Screen.CuisineScreen.route)
                }
        )
        Text(
            modifier = Modifier
                .padding(start = 16.dp),
            text = cuisine,
            color = Color.Black,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )
    }

}

@Composable
fun HorizontalFilterArea() {

    val scrollState = rememberScrollState()


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(state = scrollState),
           // .padding(top = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        RoundedBox(boxType = "filter", text = "", image = R.drawable.filter)
        RoundedBox(boxType = "sort", text = "Sort", image = R.drawable.arrow_down)
        RoundedBox(boxType = "rating", text = "Ratings 4.0+", image = R.drawable.star_)
        RoundedBox(boxType = "offers", text = "Offers", image = R.drawable.arrow_down)
        RoundedBox(boxType = "price", text = "Price", image = R.drawable.arrow_down)
        RoundedBox(boxType = "top_restaurants", text = "Top restaurant", image = R.drawable.premium)
    }

}

@Composable
fun RoundedBox(
    boxType: String,
    text: String,
    @DrawableRes image: Int? = null
) {

    Row(
        modifier = Modifier
            .border(1.dp, color = colorResource(id = R.color.rounded_light_stroke), shape = RoundedCornerShape(50.dp))
            .padding(horizontal = 16.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        when (boxType) {
            "filter" -> {
                Image(
                    painter = painterResource(id = image!!),
                    contentDescription = "Contact profile picture",
                    modifier = Modifier
                        .size(22.dp)
                        .clickable {
                            //navigateTo(Screen.CuisineScreen.route)
                        }
                )
            }

            "sort", "offers", "price" -> {
                Text(
                    modifier = Modifier,
                    text = text,
                    color = Color.Black,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                )
                Image(
                    painter = painterResource(id = image!!),
                    contentDescription = "Contact profile picture",
                    modifier = Modifier
                        //.padding(start = 5.dp)
                        .size(10.dp)
                        .clickable {
                            //navigateTo(Screen.CuisineScreen.route)
                        }
                )
            }

            "rating", "top_restaurants" -> {
                Image(
                    painter = painterResource(id = image!!),
                    contentDescription = "Contact profile picture",
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .size(12.dp)
                        .clickable {
                            //navigateTo(Screen.CuisineScreen.route)
                        }
                )
                Text(
                    modifier = Modifier,
                    text = text,
                    color = Color.Black,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                )
            }
        }

    }

}

@Composable
fun RestaurantArea(
    list: ArrayList<RestuarantItem>
) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        content = {
            items(list) { item ->
                RestaurantsCardItemVertical(item)
            }
        })

}

@Composable
fun RestaurantsCardItemVertical(restaurantItem: RestuarantItem) {

    Column(
        modifier = Modifier
            // .background(color = Color.Red)
            .fillMaxWidth()
            .padding(bottom = 12.dp)
            .wrapContentSize()
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
                        .fillMaxWidth()
                        .height(150.dp)
                )
            }
            OfferLayout(text = restaurantItem.offer)
        }
        Row(
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth(),
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
                modifier = Modifier
            )
            Spacer(modifier = Modifier.weight(1f))
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
            text = "$$ ${restaurantItem.cuisine}",
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
