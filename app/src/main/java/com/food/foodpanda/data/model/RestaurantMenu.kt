package com.food.foodpanda.data.model

import android.net.Uri
import android.os.Parcelable
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize

@Parcelize
data class RestaurantMenu(
    val title: String,
    val items: ArrayList<MenuItem>,
) : Parcelable{
    override fun toString(): String {
        return Uri.encode(Gson().toJson(this))
    }
}