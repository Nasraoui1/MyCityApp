package com.example.mycityapp.ui

import com.example.mycityapp.data.CityData
import com.example.mycityapp.model.Category
import com.example.mycityapp.model.Recommendation

data class MycityUiState(
    val categoriesList: List<Category> = emptyList(),
    val currentCategory: Category = CityData.defaultCategory,
    val currentRecommendation: Recommendation = CityData.defaultCategory.recommendations[0],
    val isShowingCategoryPage: Boolean = true,
    val isShowingRecommendationPage: Boolean = false,
    val isShowingRecommendationDetailPage: Boolean = false
)
