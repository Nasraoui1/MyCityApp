package com.example.mycityapp.ui.screens

import MycityViewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mycityapp.model.Category

@Composable
fun CategoryRecommendationScreen(
    categories: List<Category>,
    viewModel: MycityViewModel,
    navigateToRecommendationDetailScreen: (Int) -> Unit
) {
    val currentCategory = viewModel.uiState.collectAsState().value.currentCategory

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "My City",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
        )

        Row(Modifier.fillMaxSize()) {
            CategoryList(
                category = categories,
                onClick = { category ->
                    viewModel.onCategoryClick(category)
                },
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            )

            RecommendationList(
                recommendations = currentCategory.recommendations,
                onClick = { recommendation ->
                    viewModel.onRecommendationClick(recommendation)
                    navigateToRecommendationDetailScreen(recommendation.id)
                },
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }
}