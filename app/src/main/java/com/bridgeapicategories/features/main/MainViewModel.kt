package com.bridgeapicategories.features.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bridgeapicategories.domains.models.Category
import com.bridgeapicategories.domains.usecases.CategoriesUseCase
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
                .map {
                    _categories.value = it.map {
                        DisplayedCategory(
                            id = it.id,
                            name = it.name,
                            isParentCategory = true
                        )
                    }
                }.onFailure {
                   // TODO
                }
        }
    }

    fun onCategoryClicked(id: Int) {
        viewModelScope.launch {
            categoriesUseCase.getCategories().map { categories ->
                val subcategories = categories.firstOrNull { it.id == id }?.subCategories.orEmpty().map {
                    DisplayedCategory(
                        id = it.id,
                        name = it.name,
                        isParentCategory = false
                    )
                }

                val categoriesValue = _categories.value

                _categories.value = categoriesValue?.toMutableList()?.apply {
                    addAll(
                        categoriesValue.indexOfFirst { it.id == id }, subcategories
                    )
                }
            }
        }
    }
}

data class DisplayedCategory(
    val id: Int,
    val name: String,
    val isParentCategory: Boolean
)