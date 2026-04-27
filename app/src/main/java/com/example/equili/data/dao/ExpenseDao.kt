package com.example.equili.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.equili.data.model.ExpenseModel
import com.example.equili.data.model.CategoryTotal

/**
 * ExpenseDao provides the data access methods for managing expense records in the database.
 * It includes queries for basic CRUD operations and more complex statistical analysis.
 */
@Dao
interface ExpenseDao {

    /**
     * Saves an expense. If the expense already exists (matching ID), it will be updated.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(expense: ExpenseModel)

    /**
     * Removes an expense record from the database.
     */
    @Delete
    suspend fun delete(expense: ExpenseModel)

    /**
     * Fetches all expenses for a specific user, sorted from newest to oldest.
     */
    @Query("SELECT * FROM expense_table WHERE userEmail = :userEmail ORDER BY date DESC")
    fun getAllExpenses(userEmail: String): LiveData<List<ExpenseModel>>

    /**
     * Fetches expenses for a specific user that fall within a given timeframe.
     */
    @Query("SELECT * FROM expense_table WHERE userEmail = :userEmail AND date BETWEEN :startDate AND :endDate ORDER BY date DESC")
    fun getExpensesInRange(userEmail: String, startDate: Long, endDate: Long): LiveData<List<ExpenseModel>>

    /**
     * Calculates the grand total of all expenses for a specific user.
     */
    @Query("SELECT SUM(amount) FROM expense_table WHERE userEmail = :userEmail")
    fun getTotalAmount(userEmail: String): LiveData<Double?>

    /**
     * Aggregates expenses by category for a specific date range.
     * Useful for building charts and category-wise breakdowns.
     */
    @Query("SELECT category as name, SUM(amount) as total FROM expense_table WHERE userEmail = :userEmail AND date BETWEEN :startDate AND :endDate GROUP BY category")
    fun getCategoryTotalsInRange(userEmail: String, startDate: Long, endDate: Long): LiveData<List<CategoryTotal>>

    /**
     * Calculates the total amount spent in a specific category during a specific timeframe.
     */
    @Query("SELECT SUM(amount) FROM expense_table WHERE userEmail = :userEmail AND category = :categoryName AND date BETWEEN :startDate AND :endDate")
    fun getCategoryTotalInRange(userEmail: String, categoryName: String, startDate: Long, endDate: Long): LiveData<Double?>
}
