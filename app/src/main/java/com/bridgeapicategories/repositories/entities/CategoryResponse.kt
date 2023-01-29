package com.bridgeapicategories.repositories.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CategoryCoreResponse(
    @SerialName("resources")
    val resources: List<CategoryResponse>,
    @SerialName("pagination")
    val pagination: PaginationResponse
)

@Serializable
data class PaginationResponse(
    @SerialName("previous_uri")
    val previousUri: String? = null,
    @SerialName("next_uri")
    val nextUri: String? = null
)

@Serializable
data class CategoryResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("resource_uri")
    val resourceUri: String? = null,
    @SerialName("resource_type")
    val resourceType: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("parent")
    val parent: ParentCategoryResponse? = null,
    @SerialName("custom")
    val custom: Boolean? = null,
    @SerialName("other")
    val other: Boolean? = null,
    @SerialName("is_deleted")
    val isDeleted: Boolean? = null
)

@Serializable
data class ParentCategoryResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("resourceUri")
    val resourceUri: String? = null,
    @SerialName("resourceType")
    val resourceType: String? = null,
)