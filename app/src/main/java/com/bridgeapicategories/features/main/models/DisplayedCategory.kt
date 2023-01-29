package com.bridgeapicategories.features.main.models

sealed interface DisplayedCategory {
    val name: String

    data class ParentCategory(
        override val name: String,
        val id: Int,
    ) : DisplayedCategory

    data class SubCategory(
        override val name: String,
        val parentId: Int,
        val isDisplayed: Boolean
    ) : DisplayedCategory
}
