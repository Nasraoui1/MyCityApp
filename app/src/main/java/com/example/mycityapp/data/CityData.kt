package com.example.mycityapp.data

import com.example.mycityapp.R
import com.example.mycityapp.model.Category
import com.example.mycityapp.model.Recommendation


object CityData {
    val categories: List<Category> = listOf(
        Category(
            id = 1,
            name = R.string.coffee_shops_category,
            image = R.drawable.im_category2,
            recommendations = listOf(
                Recommendation(
                    id = 11,
                    name = R.string.coffee_shop_1_name,
                    image = R.drawable.im_coffee3,
                    details = R.string.recommendation_detail_text
                ),
                Recommendation(
                    id = 12,
                    name = R.string.coffee_shop_2_name,
                    image = R.drawable.im_coffee2,
                    details = R.string.recommendation_detail_text
                )
            )
        ),
        Category(
            id = 2,
            name = R.string.restaurants_category,
            image = R.drawable.im_category1,
            recommendations = listOf(
                Recommendation(
                    id = 21,
                    name = R.string.restaurant_1_name,
                    image = R.drawable.im_restau1,
                    details = R.string.recommendation_detail_text
                ),
                Recommendation(
                    id = 22,
                    name = R.string.restaurant_2_name,
                    image = R.drawable.im_restau2,
                    details = R.string.recommendation_detail_text
                )
            )
        )
    )

    val defaultCategory = categories[0]
}
