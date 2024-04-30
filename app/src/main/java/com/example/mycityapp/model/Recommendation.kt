package com.example.mycityapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 * Data model for Recommendation
 */
data class Recommendation(
    val id: Int,
    @StringRes val name: Int,
    @StringRes val details: Int,
    @DrawableRes val image: Int,

    )