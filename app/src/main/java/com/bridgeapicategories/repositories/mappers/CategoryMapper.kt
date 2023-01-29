package com.bridgeapicategories.repositories.mappers

import com.bridgeapicategories.domains.models.Category
import com.bridgeapicategories.repositories.entities.CategoryResponse

fun CategoryResponse.toCategory() = Category(
    id = id,
    resourceUri = resourceUri,
    resourceType = resourceType,
    name = name ?: "Unknown",
    subCategories = emptySet(),
    custom = custom ?: false,
    other = other ?: false,
    isDeleted = isDeleted ?: false,
)