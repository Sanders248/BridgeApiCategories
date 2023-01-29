package com.bridgeapicategories.features.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.bridgeapicategories.domains.models.Category
import com.bridgeapicategories.domains.usecases.CategoriesUseCase
import com.bridgeapicategories.features.main.models.DisplayedCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val categoriesUseCase: CategoriesUseCase
) : ViewModel() {

    private val displayedCategoryFlow = MutableStateFlow(emptySet<Int>())

    val categories: LiveData<List<DisplayedCategory>> = categoriesUseCase.categories
        .combine(displayedCategoryFlow) { categories, displayedCategories ->
            categories
                .toDisplayedCategories(displayedCategories)
                .sortedBy { it is DisplayedCategory.SubCategory }
                .sortedBy {
                    when(it) {
                        is DisplayedCategory.SubCategory -> it.parentId
                        is DisplayedCategory.ParentCategory -> it.id
                    }
                }
        }.asLiveData()

    init {
        viewModelScope.launch {
            categoriesUseCase.refresh()
        }
    }

    fun onCategoryClicked(id: Int) {
        viewModelScope.launch {
            if (id in displayedCategoryFlow.value) {
                displayedCategoryFlow.emit(displayedCategoryFlow.value - id)
            } else {
                displayedCategoryFlow.emit(displayedCategoryFlow.value + id)
            }
        }
    }

    private fun List<Category>.toDisplayedCategories(displayedCategories: Set<Int>): List<DisplayedCategory> = map { category ->
        if (category.parentId != null) DisplayedCategory.SubCategory(
            name  =  category.name,
            parentId = category.parentId,
            isDisplayed = category.parentId in displayedCategories
        )
        else DisplayedCategory.ParentCategory(
            name = category.name,
            id = category.id
        )
    }
}
