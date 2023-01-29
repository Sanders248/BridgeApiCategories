package com.bridgeapicategories.features.main.models

sealed interface DisplayedCategory {
    val id: Int
    val name: String

    data class ParentCategory(
        override val id: Int,
        override val name: String,
    ) : DisplayedCategory

    data class SubCategory(
        override val id: Int,
        override val name: String,
        val isDisplayed: Boolean
    ) : DisplayedCategory
}
