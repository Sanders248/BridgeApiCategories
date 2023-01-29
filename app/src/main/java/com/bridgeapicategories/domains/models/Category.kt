package com.bridgeapicategories.domains.models

data class Category(
    val id: Int,
    val name: String,
    val subCategories: Set<Category>,
    val resourceUri: String?,
    val resourceType: String?,
    val custom: Boolean,
    val other: Boolean,
    val isDeleted: Boolean
) {
    override fun equals(other: Any?): Boolean {
        return other != null  && other is Category && other.id == id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
