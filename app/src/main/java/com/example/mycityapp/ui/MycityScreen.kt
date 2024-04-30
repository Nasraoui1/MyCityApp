@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.mycityapp.ui

import MycityViewModel
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.mycityapp.R
import com.example.mycityapp.ui.screens.CategoryRecommendationScreen
import com.example.mycityapp.ui.screens.CategoryScreen
import com.example.mycityapp.ui.screens.RecommendationDetailScreen
import com.example.mycityapp.ui.screens.RecommendationScreen

enum class MycityScreen(@StringRes val title: Int) {
    CategoryScreen(title = R.string.category_screen_title),
    RecommendationScreen(title = R.string.recommendation_screen_title),
    RecommendationDetailScreen(title = R.string.detail_screen_title)
}

@Composable
fun MycityApp(
    windowSize: WindowWidthSizeClass,
    viewModel: MycityViewModel
) {
    val uiState = viewModel.uiState.collectAsState()
    when (windowSize) {
        WindowWidthSizeClass.Compact -> {
            when {
                uiState.value.isShowingCategoryPage -> {
                    CategoryScreen(
                        categories = uiState.value.categoriesList,
                        viewModel = viewModel,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                uiState.value.isShowingRecommendationPage -> {
                    RecommendationScreen(
                        recommendations = uiState.value.currentCategory.recommendations,
                        currentScreen = MycityScreen.RecommendationScreen,
                        canNavigateBack = true,
                        onBackClick = {
                            viewModel.onBackClick()
                        },
                        onRecommendationClick = { recommendation ->
                            viewModel.onRecommendationClick(recommendation)
                        },
                        modifier = Modifier.fillMaxSize()
                    )
                }
                else -> {
                    RecommendationDetailScreen(
                        selectedRecommendation = uiState.value.currentRecommendation,
                        currentScreen = MycityScreen.RecommendationDetailScreen,
                        canNavigateBack = true,
                        onBackClick = {
                            viewModel.onBackClick()
                        },
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
        WindowWidthSizeClass.Expanded -> {
            // Logic for expanded window size
            if (uiState.value.isShowingRecommendationDetailPage) {
                RecommendationDetailScreen(
                    selectedRecommendation = uiState.value.currentRecommendation,
                    currentScreen = MycityScreen.RecommendationDetailScreen,
                    canNavigateBack = true,
                    onBackClick = {
                        viewModel.onBackClick()
                    },
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                CategoryRecommendationScreen(
                    categories = uiState.value.categoriesList,
                    viewModel = viewModel,
                    navigateToRecommendationDetailScreen = { recommendationId ->
                        viewModel.onRecommendationClick(
                            uiState.value.currentCategory.recommendations.first { it.id == recommendationId }
                        )
                    }
                )
            }
        }
        else -> {
            // Logic for other window sizes
        }
    }
}


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MycityAppBar(
        currentScreen: MycityScreen,
        canNavigateBack: Boolean,
        navigateUp: () -> Unit,
        modifier: Modifier = Modifier
    ) {
        TopAppBar(
            title = { Text(stringResource(currentScreen.title)) },
            colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ),
            modifier = modifier,
            navigationIcon = {
                if (canNavigateBack) {
                    IconButton(onClick = navigateUp) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back_button)
                        )
                    }
                }
            }
        )
    }



