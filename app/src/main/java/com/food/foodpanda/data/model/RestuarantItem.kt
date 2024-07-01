package com.food.foodpanda.data.model

import android.graphics.drawable.Drawable
import androidx.compose.ui.graphics.painter.Painter

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
)