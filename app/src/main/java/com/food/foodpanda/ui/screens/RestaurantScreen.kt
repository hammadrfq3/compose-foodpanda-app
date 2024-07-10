package com.food.foodpanda.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.food.foodpanda.R
import com.food.foodpanda.data.model.RestuarantItem
import com.food.foodpanda.ui.navigation.Screen
import com.food.foodpanda.ui.viewmodel.RestaurantScreenViewModel

@Composable
fun RestaurantScreen(
    restaurantItem: RestuarantItem,
    //onBackPress: () -> Unit,
    viewModel: RestaurantScreenViewModel = viewModel()
) {

    Log.e("TAG", "RestaurantScreen")
    //val restaurantsData = viewModel.getRestaurantsData()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.white))
            .padding(16.dp),
    ) {
        RestaurantTopBar {
            //onBackPress.invoke()
        }
        RestaurantHeader(restaurantItem)
        RestaurantDeals()
    }

}

@Composable
fun RestaurantTopBar(
    onBackPress: () -> Unit,
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
            .padding(top = 24.dp),
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
            .padding(top = 16.dp),
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
        modifier = Modifier,
        lineHeight = 3.sp
    )
    Row(
        modifier = Modifier
            .padding(top = 16.dp),
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
            .padding(top = 16.dp),
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
fun RestaurantDeals(){

    Row(
        modifier = Modifier
            .padding(top = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.tag_sale),
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

}
