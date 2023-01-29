package com.bridgeapicategories.domains.usecases

import com.bridgeapicategories.domains.repositories.CategoriesRepository
import javax.inject.Inject

class CategoriesUseCase @Inject constructor(
    private val repository: CategoriesRepository
) {

    suspend fun getCategories() = repository.getCategories()
}