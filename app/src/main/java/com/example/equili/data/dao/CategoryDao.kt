package com.example.equili.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.equili.data.model.CategoryModel

/**
 * CategoryDao (Data Access Object) defines the database operations for expense categories.
 * This interface is used by Room to generate the underlying SQL implementation.
 */
@Dao
interface CategoryDao {

    /**
     * Inserts a new category into the database.
     * Uses OnConflictStrategy.IGNORE to avoid duplicate entries.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(category: CategoryModel)

    /**
     * Retrieves all categories associated with a specific user email.
     * Results are returned as LiveData to allow the UI to react to changes.
     * Categories are sorted alphabetically by name.
     */
    @Query("SELECT * FROM category_table WHERE userEmail = :userEmail ORDER BY name ASC")
    fun getAllCategories(userEmail: String): LiveData<List<CategoryModel>>
}
