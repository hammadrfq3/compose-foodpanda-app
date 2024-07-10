package com.food.foodpanda.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.food.foodpanda.R
import com.food.foodpanda.data.model.CardItem
import com.food.foodpanda.data.model.RestuarantItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RestaurantScreenViewModel : ViewModel() {

    private val _isReady = MutableStateFlow(false)
    val isReady = _isReady.asStateFlow()

    init {
        viewModelScope.launch {
            delay(1500L)
            _isReady.value = true
        }
    }

    fun getUpperBoxData(): ArrayList<CardItem>{

        val cardItem1 = CardItem("Food delivery","Order food you love", R.drawable.order_food)
        val cardItem2 = CardItem("Pandamart","Essentials delivered fast", R.drawable.pandamart)
        val cardItem4 = CardItem("Shops","Top brands to your door", R.drawable.shop)
        val cardItem5 = CardItem("Pandago","Send parcels in a tap", R.drawable.order_food)
        val cardItem3 = CardItem("pick-up","Self collect for 50% off", R.drawable.order_food)

        val list = ArrayList<CardItem>()
        list.add(cardItem1)
        list.add(cardItem2)
        list.add(cardItem4)
        list.add(cardItem5)
        list.add(cardItem3)

        return list

    }

    fun getRestaurantsData(): ArrayList<RestuarantItem>{

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

        return list

    }

    fun getCuisineData(): ArrayList<CardItem>{

        val cardItem1 = CardItem("Biryani","", R.drawable.cuisine_9)
        val cardItem2 = CardItem("Ice Cream","", R.drawable.cuisine_6)
        val cardItem3 = CardItem("Shakes","", R.drawable.cuisine_4)
        val cardItem4 = CardItem("Chinese","", R.drawable.cuisine_11)
        val cardItem5 = CardItem("Samosa","", R.drawable.cuisine_7)
        val cardItem6 = CardItem("Pizza","", R.drawable.cuisine_12)
        val cardItem7 = CardItem("Burgers","", R.drawable.cuisine_5)
        val cardItem8 = CardItem("Paratha","", R.drawable.cuisine_2)
        val cardItem9 = CardItem("Pulao","", R.drawable.cuisine_1)
        val cardItem10 = CardItem("BBQ","", R.drawable.cuisine_10)
        val cardItem11 = CardItem("SeaFood","", R.drawable.cuisine_8)
        val cardItem12 = CardItem("Halwa Puri","", R.drawable.cuisine_3)

        val list = ArrayList<CardItem>()
        list.add(cardItem1)
        list.add(cardItem2)
        list.add(cardItem3)
        list.add(cardItem4)
        list.add(cardItem5)
        list.add(cardItem6)
        list.add(cardItem7)
        list.add(cardItem8)
        list.add(cardItem9)
        list.add(cardItem10)
        list.add(cardItem11)
        list.add(cardItem12)

        return list

    }




}