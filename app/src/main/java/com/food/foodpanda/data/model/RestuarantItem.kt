package com.food.foodpanda.data.model

import android.net.Uri
import android.os.Parcelable
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize

@Parcelize
data class RestuarantItem(
    val title: String,
    val offer: String,
    val rating: String,
    val reviews: String,
    val minimumPricerOrder: String,
    val cuisine: String,
    val deliveryTime: String,
    val deliveryPrice: String,
    val image: Int,
) : Parcelable{
    override fun toString(): String {
        return Uri.encode(Gson().toJson(this))
    }
}