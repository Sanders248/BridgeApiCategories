package com.bridgeapicategories.features.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bridgeapicategories.domains.models.Category
import com.bridgeapicategories.domains.usecases.CategoriesUseCase
import com.bridgeapicategories.features.main.models.DisplayedCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val categoriesUseCase: CategoriesUseCase
) : ViewModel() {

    private val _categories: MutableLiveData<List<DisplayedCategory>> = MutableLiveData()
    val categories: LiveData<List<DisplayedCategory>> get() = _categories

    init {
        viewModelScope.launch {
            categoriesUseCase.getCategories()
                .map { categories ->
                    _categories.value = categories.toDisplayedCategories()
                }.onFailure {
                    // TODO
                }
        }
    }

    fun onCategoryClicked(id: Int) {
        val categories = _categories.value ?: return

        _categories.value = categories.map {
            if (it is DisplayedCategory.SubCategory && it.id == id) {
                it.copy(isDisplayed = !it.isDisplayed)
            } else it
        }
    }

    private fun Set<Category>.toDisplayedCategories(): List<DisplayedCategory> {
        val displayedCategories = mutableListOf<DisplayedCategory>()

        forEach { category ->
            displayedCategories.add(
                DisplayedCategory.ParentCategory(
                    id = category.id,
                    name = category.name,
                )
            )
            category.subCategories.forEach { subcategory ->
                displayedCategories.add(
                    DisplayedCategory.SubCategory(
                        id = category.id,
                        name = subcategory.name,
                        isDisplayed = false
                    )
                )
            }
        }
        return displayedCategories
    }
}
