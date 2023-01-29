package com.bridgeapicategories.features.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bridgeapicategories.domains.models.Category
import com.bridgeapicategories.domains.usecases.CategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val categoriesUseCase: CategoriesUseCase
) : ViewModel() {

    private val _categories: MutableLiveData<List<Category>> = MutableLiveData()
    val categories: LiveData<List<Category>> get() = _categories

    init {
        viewModelScope.launch {
            categoriesUseCase.getCategories()
                .onSuccess {
                    _categories.value = it.toList()
                }.onFailure {
                    // TODO
                }
        }
    }

}