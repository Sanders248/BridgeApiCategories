package com.bridgeapicategories.domains.usecases

import com.bridgeapicategories.domains.models.Category
import com.bridgeapicategories.domains.repositories.CategoriesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CategoriesUseCase @Inject constructor(
    private val repository: CategoriesRepository
) {

    val categories: Flow<List<Category>> = repository.categories.map {
        it.sortedBy {
            // Sort all parents categories on top
            it.parentId != null
        }
        .sortedBy {
            // Then sort all subcategories under their parents category
            it.parentId ?: it.id
        }
    }

    suspend fun refresh() = repository.refresh()
}