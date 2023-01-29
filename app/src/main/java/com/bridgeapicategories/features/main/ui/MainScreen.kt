package com.bridgeapicategories.features.main.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.bridgeapicategories.features.main.MainViewModel
import com.bridgeapicategories.features.main.ui.theme.BridgeApiCategoriesTheme
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bridgeapicategories.features.main.DisplayedCategory


@Composable
fun MainScreen(
    viewModel: MainViewModel
) {
    val categories by viewModel.categories.observeAsState(emptyList())

    CategoryList(categories, viewModel::onCategoryClicked)
}

@Composable
fun CategoryList(
    categories: List<DisplayedCategory>,
    onClick: (Int) -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        items(categories) {
            if (it.isParentCategory) {
                Text(
                    modifier = Modifier.clickable { onClick(it.id) },
                    text = it.name,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
            } else {
                Text(
                    modifier = Modifier.padding(start = 20.dp),
                    text = it.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val category = DisplayedCategory(
        id = 0,
        name = "CatName",
        isParentCategory = false
    )
    BridgeApiCategoriesTheme {
        CategoryList(listOf(category)) {}
    }
}