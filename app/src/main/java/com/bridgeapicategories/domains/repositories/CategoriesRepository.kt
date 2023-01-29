package com.bridgeapicategories.domains.repositories

import com.bridgeapicategories.domains.models.Category

interface CategoriesRepository {
    suspend fun getCategories(): Result<Set<Category>>
}