package com.food.foodpanda.ui.screens

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.food.foodpanda.R
import com.food.foodpanda.data.model.CardItem
import com.food.foodpanda.data.model.RestuarantItem
import com.food.foodpanda.ui.viewmodel.MainScreenViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(
    onVaultClick: (String) -> Unit,
    viewModel: MainScreenViewModel = viewModel()
) {

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .background(color = colorResource(id = R.color.bg))
    ) {
        TopAddressBar()
        CompositionLocalProvider(
            LocalOverscrollConfiguration provides null
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(state = scrollState)
            ) {
                TopBar()
                UpperBox()
                WhiteArea()
            }
        }

    }

}

@Composable
fun TopAddressBar(){

    Row(
        modifier = Modifier.background(
            color = colorResource(id = R.color.foodpanda)
        )
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
        ,
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.ic_menu),
            contentDescription = "Contact profile picture",
            modifier = Modifier
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
                overflow = TextOverflow.Ellipsis
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {

    var text by remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            //.padding(horizontal = 16.dp)
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.foodpanda))
            .padding(horizontal = 16.dp, vertical = 16.dp),
    ) {
        TextField(
            value = text,
            onValueChange = {
                text = it
            },
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(50.dp),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                containerColor = Color.White,
                cursorColor = colorResource(id = R.color.foodpanda)
            ),
            placeholder = {
                Text(
                    text = "Search for shops and restuarants",
                    color = colorResource(R.color.light_text)
                )
            },
            leadingIcon = @Composable {
                IconButton(
                    onClick = {
                        text = ""
                    },
                ) {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = "",
                        tint = colorResource(id = R.color.light_text)
                    )
                }
            }
        )
    }


}

@Composable
fun UpperBox(
){

    val cardItem1 = CardItem("Food delivery","Order food you love",painterResource(R.drawable.order_food))
    val cardItem2 = CardItem("Pandamart","Essentials delivered fast",painterResource(R.drawable.pandamart))
    val cardItem4 = CardItem("Shops","Top brands to your door",painterResource(R.drawable.shop))
    val cardItem5 = CardItem("Pandago","Send parcels in a tap",painterResource(R.drawable.order_food))
    val cardItem3 = CardItem("pick-up","Self collect for 50% off",painterResource(R.drawable.order_food))

    val list = ArrayList<CardItem>()
    list.add(cardItem1)
    list.add(cardItem2)
    list.add(cardItem4)
    list.add(cardItem5)
    list.add(cardItem3)

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
                painter = cardItem.image,
                contentDescription = "Contact profile picture",
                modifier = imageModifier
                    .align(Alignment.BottomEnd)
                //.background(color = Color.Blue)
            )
        }
    }


}

@Composable
fun WhiteArea(){

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
        OrderItAgain()
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
                "30 mins"
            )
            TopBrands(
                R.drawable.papa_johns,
                "Papa Johns",
                "30 mins"
            )
            TopBrands(
                R.drawable.baskin_robbins_logo,
                "Baskin Robbins",
                "26 mins"
            )
        }
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
fun OrderItAgain() {

    val item1 =
        RestuarantItem(
            title = "Dogar Restuarant & Biryani",
            offer = "Up to 20% off",
            image = R.drawable.food_1,
            rating = "4.6",
            reviews = "500+",
            minimumPricerOrder = "PKR 249 minimum",
            cuisine = "Pakistani",
            deliveryTime = "15-30 min",
            deliveryPrice = "69 PKR"
        )


    val item2 =
        RestuarantItem(
            title = "Ice Land & Juice Corner",
            offer = "Featured",
            image = R.drawable.food_2,
            rating = "4.9",
            reviews = "500+",
            minimumPricerOrder = "PKR 249 minimum",
            cuisine = "Desserts",
            deliveryTime = "15-30 min",
            deliveryPrice = "199.0 PKR"
        )


    val item3 =
        RestuarantItem(
            title = "Karachi Hot N Spicy",
            offer = "Up to 20% off",
            image = R.drawable.food_3,
            rating = "4.6",
            reviews = "500+",
            minimumPricerOrder = "PKR 249 minimum",
            cuisine = "Continental",
            deliveryTime = "15-30 min",
            deliveryPrice = "99 PKR"
        )


    val item4 =
        RestuarantItem(
            title = "Swad - Bahria Town",
            offer = "Up to 20% off",
            image = R.drawable.food_4,
            rating = "4.6",
            reviews = "500+",
            minimumPricerOrder = "PKR 249 minimum",
            cuisine = "Pakistani",
            deliveryTime = "15-30 min",
            deliveryPrice = "69 PKR"
        )


    val list = ArrayList<RestuarantItem>()
    list.add(item1)
    list.add(item2)
    list.add(item3)
    list.add(item4)

        LazyHorizontalGrid(
            rows = GridCells.Fixed(1),
            content = {
                items(list) { item ->
                    RestuarantsCardItem(item)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        )

}

@Composable
fun RestuarantsCardItem(restuarantItem: RestuarantItem) {

    Column(
        modifier = Modifier
            // .background(color = Color.Red)
            .padding(end = 10.dp)
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
                    painter = painterResource(id = restuarantItem.image),
                    contentDescription = "Contact profile picture",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(270.dp)
                        .height(150.dp)
                )
            }
            OfferLayout(text = restuarantItem.offer)
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
                text = restuarantItem.title,
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
                text = restuarantItem.rating,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.black),
                fontSize = 12.sp,
                lineHeight = 3.sp,
                modifier = Modifier
                   // .fillMaxWidth(.1f)
            )
            Text(
                text = "(${restuarantItem.reviews})",
                fontWeight = FontWeight.Normal,
                color = colorResource(id = R.color.light_text),
                fontSize = 12.sp,
                lineHeight = 3.sp,
                modifier = Modifier
                   // .fillMaxWidth(.1f)
            )
        }
        Text(
            text = "$$ ${restuarantItem.minimumPricerOrder} - ${restuarantItem.cuisine}",
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
                text = restuarantItem.deliveryTime,
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
                text = restuarantItem.deliveryPrice,
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
    title:String,
    distance:String
){


    Column(
        modifier = Modifier
            .padding(top = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier,
            colors = CardColors(
                containerColor = colorResource(id = R.color.bg),
                contentColor = Color.Transparent,
                disabledContentColor = Color.Transparent,
                disabledContainerColor = Color.Transparent
            )
        ) {
            Image(
                painter = painterResource(id = logo),
                contentDescription = "Contact profile picture",
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
                    .padding(10.dp)
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