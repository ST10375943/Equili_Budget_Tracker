package com.example.equili.data.model

/**
 * CategoryTotal is a helper data class (not a database entity) used for reporting.
 * It holds the aggregate spending total for a specific category, often used in charts or lists.
 */
data class CategoryTotal(
    /** The name of the category. */
    val name: String,

    /** The calculated sum of all expenses within this category. */
    val total: Double
)
