package com.bridgeapicategories.repositories.injection

import android.content.Context
import androidx.room.Room
import com.bridgeapicategories.domains.repositories.CategoriesRepository
import com.bridgeapicategories.repositories.apiservices.BridgeCategoriesApiService
import com.bridgeapicategories.repositories.CategoriesRepositoryImpl
import com.bridgeapicategories.repositories.localservice.CategoryRoomDatabase
import com.bridgeapicategories.repositories.localservice.ParentCategoryTableConverter
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

        @Provides
        fun provideCharacterDatabase(
            @ApplicationContext context: Context
        ) = Room.databaseBuilder(
            context,
            CategoryRoomDatabase::class.java,
            "category_db"
        )
            .addTypeConverter(ParentCategoryTableConverter())
            .build()

        @Provides
        fun provideCharacterDao(db: CategoryRoomDatabase) = db.categoryDao()
    }
}