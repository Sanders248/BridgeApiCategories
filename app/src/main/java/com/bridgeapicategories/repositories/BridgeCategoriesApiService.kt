package com.bridgeapicategories.repositories

import com.bridgeapicategories.repositories.entities.CategoryResponse
import retrofit2.Response
import retrofit2.http.GET

interface BridgeCategoriesApiService {
    @GET("categories.json")
    suspend fun getCategories(): Response<List<CategoryResponse>>
}