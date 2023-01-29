package com.bridgeapicategories.repositories

import com.bridgeapicategories.domains.models.Category
import com.bridgeapicategories.domains.repositories.CategoriesRepository
import com.bridgeapicategories.libraries.network.apiCall
import com.bridgeapicategories.repositories.mappers.toCategory
import javax.inject.Inject

class CategoriesRepositoryImpl @Inject constructor(
    private val apiService: BridgeCategoriesApiService
): CategoriesRepository {
    override suspend fun getCategories(): Result<List<Category>> = apiCall {
        apiService.getCategories()
    }.map {
        it.map(::toCategory)
    }
}