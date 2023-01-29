package com.bridgeapicategories.repositories.injection

import com.bridgeapicategories.domains.repositories.CategoriesRepository
import com.bridgeapicategories.repositories.BridgeCategoriesApiService
import com.bridgeapicategories.repositories.CategoriesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit


@Module
@InstallIn(SingletonComponent::class)
interface CategoriesRepositoryModule {
    @Binds
    fun bindCharacterRepository(impl: CategoriesRepositoryImpl): CategoriesRepository

    companion object {
        @Provides
        fun provideCharacterApiService(
            retrofit: Retrofit
        ): BridgeCategoriesApiService = retrofit.create(BridgeCategoriesApiService::class.java)
    }
}