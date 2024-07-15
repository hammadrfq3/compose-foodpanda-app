package com.food.foodpanda.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.food.foodpanda.R
import com.food.foodpanda.data.model.CardItem
import com.food.foodpanda.data.model.MenuItem
import com.food.foodpanda.data.model.RestaurantMenu
import com.food.foodpanda.data.model.RestuarantItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RestaurantScreenViewModel : ViewModel() {


    fun getRestaurantMenu(): Triple<ArrayList<RestaurantMenu>,ArrayList<MenuItem>,String>{

        val item1 = MenuItem("Single Nashta",R.drawable.cuisine_3, "Rs. 250.00")
        val item2 = MenuItem("Lassi",R.drawable.lassi, "Rs. 200.00")
        val item3 = MenuItem("Sada Chany",R.drawable.chany, "Rs. 199.00")
        val item4 = MenuItem("Daal Mash",R.drawable.daal_mash, "Rs. 200.00")
        val item5 = MenuItem("Aloo Paratha",R.drawable.aloo_paratha, "Rs. 160.00")
        val item6 = MenuItem("Sada Haleem",R.drawable.haleem, "Rs. 374.00")

        val list = ArrayList<MenuItem>()
        list.add(item1)
        list.add(item2)
        list.add(item3)
        list.add(item4)
        list.add(item5)
        list.add(item6)

        val popular = RestaurantMenu("Popular",list)

        val item7 = MenuItem("Single Nashta",R.drawable.cuisine_3, "Rs. 250.00")
        val item8 = MenuItem("Puri",R.drawable.puri, "Rs. 110.00")
        val item9 = MenuItem("Halwa",R.drawable.halwa, "Rs. 499.00")
        val item10 = MenuItem("Beef Nihari",R.drawable.beef_nihari, "Rs. 599.00")
        val item11 = MenuItem("Chicken Nihari",R.drawable.chicken_nihari, "Rs. 499.00")
        val item12 = MenuItem("Murgh Chanay",R.drawable.murgh_chany, "Rs. 450.00")
        val item13 = MenuItem("Anda Chanay",R.drawable.chany, "Rs. 270.00")

        val list2 = ArrayList<MenuItem>()
        list2.add(item7)
        list2.add(item8)
        list2.add(item9)
        list2.add(item10)
        list2.add(item11)
        list2.add(item12)
        list2.add(item13)

        val allData = ArrayList<MenuItem>()
        allData.addAll(list)
        allData.addAll(list2)

        val special = RestaurantMenu("Special Nashta",list2)

        return Triple(arrayListOf(popular,special),allData,"")

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