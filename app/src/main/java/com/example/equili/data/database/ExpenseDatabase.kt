package com.example.equili.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.equili.data.dao.*
import com.example.equili.data.model.*

/**
 * ExpenseDatabase is the main Room database class for the Equili app.
 * It manages local persistence for expenses, categories, goals, and user profiles.
 */
@Database(
    entities = [ExpenseModel::class, CategoryModel::class, GoalModel::class, UserModel::class],
    version = 6,
    exportSchema = false
)
abstract class ExpenseDatabase : RoomDatabase() {

    // Access points to the Data Access Objects (DAOs)
    abstract fun expenseDao(): ExpenseDao
    abstract fun categoryDao(): CategoryDao
    abstract fun goalDao(): GoalDao
    abstract fun userDao(): UserDao

    companion object {
        /**
         * Singleton pattern for the database instance.
         * Using @Volatile ensures the instance is always up-to-date across threads.
         */
        @Volatile
        private var INSTANCE: ExpenseDatabase? = null

        /**
         * Returns the singleton instance of the database.
         * Synchronized block ensures thread-safety during creation.
         */
        fun getDatabase(context: Context): ExpenseDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        ExpenseDatabase::class.java,
                        "expense_database"
                )
                /**
                 * fallbackToDestructiveMigration:
                 * Wipes and rebuilds the database when the version number increases.
                 * Useful during development when schema changes frequently.
                 */
                .fallbackToDestructiveMigration()
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
