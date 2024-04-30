package com.example.mycityapp.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mycityapp.R
import com.example.mycityapp.model.Recommendation
import com.example.mycityapp.ui.MycityAppBar
import com.example.mycityapp.ui.MycityScreen

@Composable
fun RecommendationDetailScreen(
    modifier: Modifier,
    selectedRecommendation: Recommendation,
    currentScreen: MycityScreen,
    canNavigateBack: Boolean,
    onBackClick: () -> Unit
) {
    val contentPadding = PaddingValues(16.dp)

    Column {
        MycityAppBar(
            currentScreen = currentScreen,
            canNavigateBack = canNavigateBack,
            navigateUp = onBackClick
        )

        RecommendationDetail(
            selectedRecommendation = selectedRecommendation,
            onBackPressed = onBackClick,
            contentPadding = contentPadding
        )
    }
}

@Composable
fun RecommendationDetail(
    selectedRecommendation: Recommendation,
    onBackPressed: () -> Unit,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    BackHandler {
        onBackPressed()
    }
    val scrollState = rememberScrollState()
    val layoutDirection = LocalLayoutDirection.current
    Box(
        modifier = modifier
            .verticalScroll(state = scrollState)
            .padding(top = contentPadding.calculateTopPadding())
    ) {
        Column(
            modifier = Modifier
                .padding(
                    bottom = contentPadding.calculateTopPadding(),
                    start = contentPadding.calculateStartPadding(layoutDirection),
                    end = contentPadding.calculateEndPadding(layoutDirection)
                )
        ) {
            Box {
                Box() {
                    Image(
                        painter = painterResource(selectedRecommendation.image),
                        contentDescription = null,
                        alignment = Alignment.TopCenter,
                        contentScale = ContentScale.Fit,
                    )
                }
                Column(
                    Modifier
                        .align(Alignment.BottomStart)
                        .fillMaxWidth()
                        .background(
                            Brush.verticalGradient(
                                listOf(Color.Transparent, MaterialTheme.colorScheme.scrim),
                                0f,
                                400f
                            )
                        )
                ) {
                    Text(
                        text = stringResource(selectedRecommendation.name),
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.inverseOnSurface,
                        modifier = Modifier
                            .padding(horizontal = dimensionResource(R.dimen.padding_small))
                    )
                }
            }
            Text(
                text = stringResource(selectedRecommendation.details),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(
                    vertical = dimensionResource(R.dimen.padding_detail_content_vertical),
                    horizontal = dimensionResource(R.dimen.padding_detail_content_horizontal)
                )
            )
        }
    }
}


@Preview
@Composable
fun RecommendationDetailPreview() {
    // Replace with a sample Recommendation from your data
    val sampleRecommendation = Recommendation(
        id = 11,
        name = R.string.coffee_shop_1_name,
        image = R.drawable.im_coffee1,
        details = R.string.recommendation_detail_text
    )

    RecommendationDetail(
        selectedRecommendation = sampleRecommendation,
        onBackPressed = {},
        contentPadding = PaddingValues(0.dp) // Add this line

    )
}
