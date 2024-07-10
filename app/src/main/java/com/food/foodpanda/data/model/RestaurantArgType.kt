package com.food.foodpanda.data.model

import com.google.gson.Gson

class RestaurantArgType : JsonNavType<RestuarantItem>() {
    override fun fromJsonParse(value: String): RestuarantItem =
        Gson().fromJson(value, RestuarantItem::class.java)

    override fun RestuarantItem.getJsonParse(): String = Gson().toJson(this)
}