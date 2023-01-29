package com.bridgeapicategories.repositories.localservice

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [CategoryTable::class], version = 1, exportSchema = false)
@TypeConverters(ParentCategoryTableConverter::class)
abstract class CategoryRoomDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
}