package com.bridgeapicategories.repositories.apiservices

import com.bridgeapicategories.repositories.localservice.CategoryTable
import com.bridgeapicategories.repositories.localservice.ParentCategoryTable

fun CategoryResponse.toCategoryTable() = CategoryTable(
    id = id,
    resourceUri = resourceUri,
    resourceType = resourceType,
    name = name ?: "Unknown",
    parent = parent?.toParentCategory(),
    custom = custom ?: false,
    other = other ?: false,
    isDeleted = isDeleted ?: false,
)

fun ParentCategoryResponse.toParentCategory() = ParentCategoryTable(
    id = id,
    resourceUri = resourceUri,
    resourceType = resourceType
)