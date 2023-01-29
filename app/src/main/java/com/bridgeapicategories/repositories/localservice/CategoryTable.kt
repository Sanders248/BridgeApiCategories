package com.bridgeapicategories.repositories.localservice

import androidx.room.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


@Entity(tableName = "category_table")
data class CategoryTable(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo("name")
    val name: String,
    @ColumnInfo("parent")
    val parent: ParentCategoryTable? = null,
    @ColumnInfo("resource_uri")
    val resourceUri: String? = null,
    @ColumnInfo("resource_type")
    val resourceType: String? = null,
    @ColumnInfo("custom")
    val custom: Boolean,
    @ColumnInfo("other")
    val other: Boolean,
    @ColumnInfo("is_deleted")
    val isDeleted: Boolean
)

@Serializable
data class ParentCategoryTable(
    @SerialName("id")
    val id: Int,
    @SerialName("resource_uri")
    val resourceUri: String? = null,
    @SerialName("resource_type")
    val resourceType: String? = null,
)

@ProvidedTypeConverter
class ParentCategoryTableConverter {
    @TypeConverter
    fun StringToLocationTable(list: String?): ParentCategoryTable? =
        list?.let { Json.decodeFromString<ParentCategoryTable>(it) }

    @TypeConverter
    fun LocationTableToString(locationTable: ParentCategoryTable?): String? = locationTable?.let {
        Json.encodeToString(it)
    }
}