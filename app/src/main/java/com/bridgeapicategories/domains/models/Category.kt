package com.bridgeapicategories.domains.models

data class Category(
    val id: Int,
    val name: String,
    val resourceUri: String?,
    val resourceType: String?,
    val parent: ParentCategory?,
    val custom: Boolean,
    val other: Boolean,
    val isDeleted: Boolean
)

data class ParentCategory(
    val id: Int,
    val resourceUri: String?,
    val resourceType: String?,
)

