package com.food.foodpanda.data.model

import android.graphics.drawable.Drawable
import androidx.compose.ui.graphics.painter.Painter

data class CardItem(
    val title: String,
    val desc: String,
    val image: Painter,
)