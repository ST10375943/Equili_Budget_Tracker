package com.example.equili.repository

import androidx.lifecycle.LiveData
import com.example.equili.data.dao.*
import com.example.equili.data.model.*
import com.example.equili.data.database.ExpenseDatabase

/**
 * ExpenseRepository acts as a mediator between the ViewModel and the underlying Room database.
 * It provides a clean API for the rest of the app to access and modify data.
 */
class ExpenseRepository(private val db: ExpenseDatabase) {

    // Access to specific DAOs
    private val expenseDao = db.expenseDao()
    private val categoryDao = db.categoryDao()
    private val goalDao = db.goalDao()
    private val userDao = db.userDao()

    // --- Expense Operations ---

    fun getAllExpenses(userEmail: String): LiveData<List<ExpenseModel>> = expenseDao.getAllExpenses(userEmail)
    fun getTotalAmount(userEmail: String): LiveData<Double?> = expenseDao.getTotalAmount(userEmail)
    fun getExpensesInRange(userEmail: String, start: Long, end: Long) = expenseDao.getExpensesInRange(userEmail, start, end)
    fun getCategoryTotalsInRange(userEmail: String, start: Long, end: Long) = expenseDao.getCategoryTotalsInRange(userEmail, start, end)
    fun getCategoryTotalInRange(userEmail: String, name: String, start: Long, end: Long) =
        expenseDao.getCategoryTotalInRange(userEmail, name, start, end)

    suspend fun insertExpense(expense: ExpenseModel) = expenseDao.insert(expense)
    suspend fun deleteExpense(expense: ExpenseModel) = expenseDao.delete(expense)

    // --- Category Operations ---

    fun getAllCategories(userEmail: String): LiveData<List<CategoryModel>> = categoryDao.getAllCategories(userEmail)
    suspend fun insertCategory(category: CategoryModel) = categoryDao.insert(category)

    // --- Goal Operations ---

    fun getMonthlyGoal(userEmail: String): LiveData<GoalModel?> = goalDao.getGoal(userEmail)
    suspend fun updateGoal(goal: GoalModel) = goalDao.updateGoal(goal)

    // --- User Profile & Auth Operations ---

    suspend fun registerUser(user: UserModel) = userDao.registerUser(user)
    suspend fun getUserByEmail(email: String) = userDao.getUserByEmail(email)
    fun getUserByEmailLiveData(email: String) = userDao.getUserByEmailLiveData(email)
    suspend fun updateUser(user: UserModel) = userDao.updateUser(user)
}
