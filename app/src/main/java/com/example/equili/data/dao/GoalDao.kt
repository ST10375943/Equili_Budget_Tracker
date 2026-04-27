package com.example.equili.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.equili.data.model.GoalModel

/**
 * GoalDao manages the monthly budget goals set by users.
 */
@Dao
interface GoalDao {

    /**
     * Updates the user's monthly spending goals.
     * If a goal already exists for the user, it is replaced.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateGoal(goal: GoalModel)

    /**
     * Retrieves the current goal configuration for a specific user.
     */
    @Query("SELECT * FROM goal_table WHERE userEmail = :userEmail")
    fun getGoal(userEmail: String): LiveData<GoalModel?>
}
