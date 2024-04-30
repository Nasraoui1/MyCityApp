package com.example.mycityapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mycityapp.R
import com.example.mycityapp.model.Recommendation
import com.example.mycityapp.ui.MycityAppBar
import com.example.mycityapp.ui.MycityScreen
import com.example.mycityapp.ui.theme.MyCityAppTheme


@Composable
fun RecommendationScreen(
    modifier: Modifier,
    recommendations: List<Recommendation>,
    currentScreen: MycityScreen,
    canNavigateBack: Boolean,
    onBackClick: () -> Unit,
    onRecommendationClick: (Recommendation) -> Unit
) {
    Column {
        MycityAppBar(
            currentScreen = currentScreen,
            canNavigateBack = canNavigateBack,
            navigateUp = onBackClick
        )

        RecommendationList(
            recommendations = recommendations,
            onClick = onRecommendationClick
        )
    }
}

@Composable
fun RecommendationList(
    recommendations: List<Recommendation>,
    onClick: (Recommendation) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    LazyColumn(
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
        modifier = modifier.padding(top = dimensionResource(R.dimen.padding_medium)),
    ) {
        items(recommendations, key = { recommendation -> recommendation.id }) { recommendation ->
            RecommendationItem(
                recommendation = recommendation,
                onItemClick = onClick
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecommendationItem(recommendation: Recommendation,
                       onItemClick: (Recommendation) -> Unit,
                       modifier: Modifier = Modifier)
{
    Card(
        elevation = CardDefaults.cardElevation(),
        modifier = modifier,
        shape = RoundedCornerShape(dimensionResource(R.dimen.card_corner_radius)),
        onClick = { onItemClick(recommendation) }
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .size(dimensionResource(R.dimen.card_image_height))
        ){
            RecommendationImageListItem(
                recommendation = recommendation,
                modifier = Modifier
                    .size(dimensionResource(R.dimen.card_image_height))
                    .weight(1.2f)
                    .fillMaxHeight()
            )
            Column(
                modifier = Modifier
                    .padding(
                        vertical = dimensionResource(R.dimen.padding_small),
                        horizontal = dimensionResource(R.dimen.padding_medium)
                    )
                    .weight(1f)
            ) {
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = stringResource(recommendation.name),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = dimensionResource(R.dimen.card_text_vertical_space))
                )
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
private fun RecommendationImageListItem(recommendation: Recommendation, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(recommendation.image),
            contentDescription = null,
            alignment = Alignment.Center,
            contentScale = ContentScale.Crop
        )
    }
}

@Preview
@Composable
fun RecommendationItemPreview() {
    val sampleRecommendation = Recommendation(
        id = 11,
        name = R.string.coffee_shop_1_name,
        image = R.drawable.im_coffee1,
        details = R.string.recommendation_detail_text
    )

    RecommendationItem(
        recommendation = sampleRecommendation,
        onItemClick = {}
    )
}

@Preview
@Composable
fun RecommendationListPreview() {
    MyCityAppTheme {
        Surface {
            // Replace with a list of sample Recommendations from your data
            val sampleRecommendations = listOf(
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
                // Add more sample Recommendations as needed
            )

            RecommendationList(
                recommendations = sampleRecommendations,
                onClick = {},
            )
        }
    }
}


