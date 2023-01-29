package com.bridgeapicategories.repositories.localservice

import com.bridgeapicategories.domains.models.Category

fun toCategory(categoryTable: CategoryTable) = with(categoryTable) {
    Category(
        id = id,
        name = name,
        parentId = categoryTable.parent?.id
    )
}