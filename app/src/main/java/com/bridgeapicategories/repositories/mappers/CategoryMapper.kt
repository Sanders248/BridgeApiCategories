package com.bridgeapicategories.repositories.mappers

import com.bridgeapicategories.domains.models.Category
import com.bridgeapicategories.domains.models.ParentCategory
import com.bridgeapicategories.repositories.entities.CategoryResponse
import com.bridgeapicategories.repositories.entities.ParentCategoryResponse

fun toCategory(categoryResponse: CategoryResponse) = with(categoryResponse) {
    Category(
        id = id,
        resourceUri = resourceUri,
        resourceType = resourceType,
        name = name ?: "Unknown",
        parent = parent?.toParentCategory(),
        custom = custom ?: false,
        other = other ?: false,
        isDeleted = isDeleted ?: false,
    )
}

private fun ParentCategoryResponse.toParentCategory() = ParentCategory(
    id = id,
    resourceUri = resourceUri,
    resourceType = resourceType,
)