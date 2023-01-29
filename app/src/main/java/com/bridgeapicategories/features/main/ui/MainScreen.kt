package com.bridgeapicategories.features.main.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.bridgeapicategories.domains.models.Category
import com.bridgeapicategories.features.main.MainViewModel
import com.bridgeapicategories.ui.theme.BridgeApiCategoriesTheme
import androidx.compose.runtime.livedata.observeAsState


@Composable
fun MainScreen(
    viewModel: MainViewModel
) {
    val categories by viewModel.categories.observeAsState(emptyList())

    CategoryList(categories)
}

@Composable
fun CategoryList(
    categories: List<Category>
) {
    LazyColumn()  {
        items(categories)  {
            Text(text = it.name)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val category = Category(
        id = 0,
        name = "CatName",
        subCategories = emptySet(),
        resourceUri = null,
        resourceType = null,
        custom = false,
        other = false,
        isDeleted = false,
    )
    BridgeApiCategoriesTheme {
        CategoryList(listOf(category))
    }
}