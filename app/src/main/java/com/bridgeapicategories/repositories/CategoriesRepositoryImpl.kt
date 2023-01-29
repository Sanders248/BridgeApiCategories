package com.bridgeapicategories.repositories

import com.bridgeapicategories.domains.models.Category
import com.bridgeapicategories.domains.repositories.CategoriesRepository
import com.bridgeapicategories.libraries.network.apiCall
import com.bridgeapicategories.repositories.apiservices.BridgeCategoriesApiService
import com.bridgeapicategories.repositories.apiservices.toCategoryTable
import com.bridgeapicategories.repositories.localservice.CategoryDao
import com.bridgeapicategories.repositories.localservice.toCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CategoriesRepositoryImpl @Inject constructor(
    private val apiService: BridgeCategoriesApiService,
    private val categoryDao: CategoryDao
): CategoriesRepository {

    override val categories: Flow<List<Category>> = categoryDao.getAlphabetizedCategories().map {
        it.map(::toCategory)
    }.distinctUntilChanged()

    override suspend fun refresh(): Result<Unit> = apiCall {
        apiService.getCategories()
    }.map { categoryCoreResponse ->
       categoryCoreResponse.resources.map {
           it.toCategoryTable()
       }
    }.onSuccess {
        categoryDao.insertAll(it)
    }.map { Result.success(Unit) }
}