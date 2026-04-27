package com.example.equili.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

/**
 * GoalModel represents the user's monthly spending limits.
 * The primary key is the userEmail, ensuring each user has exactly one set of goals.
 */
@Entity(tableName = "goal_table")
data class GoalModel(

    /** The user's email, acting as a unique identifier for their goal settings. */
    @PrimaryKey
    val userEmail: String,

    /** The minimum spending threshold the user aims for. */
    val minGoal: Double,

    /** The maximum spending limit for the month. */
    val maxGoal: Double
) : Serializable
