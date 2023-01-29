package com.bridgeapicategories.repositories.localservice

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Query("SELECT * FROM category_table ORDER BY name ASC")
    fun getAlphabetizedCategories(): Flow<List<CategoryTable>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(character: CategoryTable)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    suspend fun insertAll(characters: List<CategoryTable>)

    @Query("DELETE FROM category_table")
    suspend fun deleteAll()
}