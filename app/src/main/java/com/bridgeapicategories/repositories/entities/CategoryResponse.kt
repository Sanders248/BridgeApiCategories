package com.bridgeapicategories.repositories.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CategoryResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("resource_uri")
    val resourceUri: String,
    @SerialName("resource_type")
    val resourceType: String,
    @SerialName("name")
    val name: String,
    @SerialName("parent")
    val parent: ParentCategoryResponse?,
    @SerialName("custom")
    val custom: Boolean,
    @SerialName("other")
    val other: Boolean,
    @SerialName("is_deleted")
    val isDeleted: Boolean
)

@Serializable
data class ParentCategoryResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("resourceUri")
    val resourceUri: String,
    @SerialName("resourceType")
    val resourceType: String,
)