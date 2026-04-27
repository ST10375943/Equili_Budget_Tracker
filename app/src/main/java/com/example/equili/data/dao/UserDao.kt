package com.example.equili.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.equili.data.model.UserModel

/**
 * UserDao manages user profile data, including registration, authentication, and stats.
 */
@Dao
interface UserDao {

    /**
     * Registers a new user.
     * If the email is already in the database, the operation is ignored.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun registerUser(user: UserModel): Long

    /**
     * Updates user information, such as XP, level, or streak.
     */
    @Update
    suspend fun updateUser(user: UserModel)

    /**
     * Finds a user profile by email. Primarily used during the login process.
     */
    @Query("SELECT * FROM user_table WHERE email = :email LIMIT 1")
    suspend fun getUserByEmail(email: String): UserModel?

    /**
     * Retrieves the user profile as LiveData for reactive UI updates (e.g., updating level/XP on dashboard).
     */
    @Query("SELECT * FROM user_table WHERE email = :email LIMIT 1")
    fun getUserByEmailLiveData(email: String): LiveData<UserModel?>
}
