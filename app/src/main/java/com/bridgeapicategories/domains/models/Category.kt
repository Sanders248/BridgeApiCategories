package com.bridgeapicategories.domains.models

data class Category(
    val id: Int,
    val name: String,
    val parentId: Int?,
)