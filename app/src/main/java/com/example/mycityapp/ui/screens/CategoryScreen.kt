package com.example.mycityapp.ui.screens

import MycityViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
import com.example.mycityapp.data.CityData
import com.example.mycityapp.model.Category
import com.example.mycityapp.ui.theme.MyCityAppTheme


@Composable
fun CategoryScreen(
    modifier: Modifier,
    categories: List<Category>,
    viewModel: MycityViewModel,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "My City",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
        )

        CategoryList(
            category = categories,
            onClick = { category ->
                viewModel.onCategoryClick(category)
            },
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}


@Composable
fun CategoryList(
    category: List<Category>,
    onClick: (Category) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    LazyColumn(
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
        modifier = modifier.padding(top = dimensionResource(R.dimen.padding_medium)),
    ) {
        items(category, key = { category -> category.id }) { category ->
            CategoryItem(
                category = category,
                onItemClick = onClick
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryItem(category: Category ,
                 onItemClick: (Category) -> Unit,
                 modifier: Modifier = Modifier)
{
    Card(
        elevation = CardDefaults.cardElevation(),
        modifier = modifier,
        shape = RoundedCornerShape(dimensionResource(R.dimen.card_corner_radius)),
        onClick = { onItemClick(category) }
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .size(dimensionResource(R.dimen.card_image_height))
        ){ CategoryImageListItem(
            category = category,
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
                    text = stringResource(category.name),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = dimensionResource(R.dimen.card_text_vertical_space))
                )
                Spacer(modifier = Modifier.weight(1f))

            }
        }
    }
}

@Composable
private fun CategoryImageListItem(category: Category, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(category.image),
            contentDescription = null,
            alignment = Alignment.Center,
            contentScale = ContentScale.Crop
        )
    }
}

@Preview
@Composable
fun CategoryItemPreview() {

        CategoryItem(
            category = CityData.defaultCategory,
            onItemClick = {}
        )

}
@Preview
@Composable
fun CategoryListPreview() {
    MyCityAppTheme {
        Surface {
            CategoryList(
                category = CityData.categories,
                onClick = {},
            )
        }
    }
}