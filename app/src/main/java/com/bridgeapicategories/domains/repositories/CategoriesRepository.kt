package com.bridgeapicategories.domains.repositories

import com.bridgeapicategories.domains.models.Category
import kotlinx.coroutines.flow.Flow

interface CategoriesRepository {
    val categories: Flow<List<Category>>

    suspend fun refresh(): Result<Unit>
}