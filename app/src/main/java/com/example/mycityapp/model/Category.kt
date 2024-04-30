package com.example.mycityapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 * Data model for Category
 */
data class Category(
    val id: Int,
    @StringRes val name: Int,
    val recommendations: List<Recommendation>,
    @DrawableRes val image: Int,


    )