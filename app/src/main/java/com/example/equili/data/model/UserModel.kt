package com.example.equili.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * UserModel represents a registered user in the system.
 * It stores credentials and gamification metrics like XP, Level, and Streak.
 */
@Entity(tableName = "user_table")
data class UserModel(

    /** User's unique email address, used for login and linking data. */
    @PrimaryKey
    val email: String,

    /** User's password for authentication. */
    val password: String,

    /** Accumulated Experience Points (XP) for gamification. */
    val xp: Int = 0,

    /** Current user level based on XP. */
    val level: Int = 1,

    /** Number of consecutive days the user has engaged with the app. */
    val streak: Int = 0,

    /** The timestamp of the last significant interaction, used to calculate streaks. */
    val lastActionDate: Long = 0
)
