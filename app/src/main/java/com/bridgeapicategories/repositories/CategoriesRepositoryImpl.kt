package com.bridgeapicategories.repositories

import com.bridgeapicategories.domains.models.Category
import com.bridgeapicategories.domains.repositories.CategoriesRepository
import com.bridgeapicategories.libraries.network.apiCall
import com.bridgeapicategories.repositories.mappers.toCategory
import javax.inject.Inject

class CategoriesRepositoryImpl @Inject constructor(
    private val apiService: BridgeCategoriesApiService
): CategoriesRepository {
    override suspend fun getCategories(): Result<Set<Category>> = apiCall {
        apiService.getCategories()
    }.map { categoryCoreResponse ->
        val categoriesResponse = categoryCoreResponse.resources
        val categories  = mutableListOf<Category>()

        // Add all parents categories
        categoriesResponse.forEach { categoryResponse ->
            if (categoryResponse.parent == null) {
                categories.add(categoryResponse.toCategory())
            }
        }

        // Add all subcategories in their parents
        categoriesResponse.forEach { categoryResponse ->
            if (categoryResponse.parent != null) {
                categories.firstOrNull { it.id == categoryResponse.parent.id }?.let { parent ->
                    val subCategories = parent.subCategories.toMutableSet().apply {
                        add(categoryResponse.toCategory())
                    }
                    val parentUpdated = parent.copy(subCategories = subCategories)

                    categories.removeIf { it.id == categoryResponse.parent.id }
                    categories.add(parentUpdated)
                }
            }
        }

        categories.toSet()
    }
}