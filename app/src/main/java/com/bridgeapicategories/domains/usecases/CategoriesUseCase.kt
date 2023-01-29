package com.bridgeapicategories.domains.usecases

import com.bridgeapicategories.domains.models.Category
import com.bridgeapicategories.domains.repositories.CategoriesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoriesUseCase @Inject constructor(
    private val repository: CategoriesRepository
) {

    val categories: Flow<List<Category>> = repository.categories

    suspend fun refresh() = repository.refresh()
}