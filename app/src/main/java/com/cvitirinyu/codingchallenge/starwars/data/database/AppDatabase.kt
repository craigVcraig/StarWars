package com.cvitirinyu.codingchallenge.starwars.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cvitirinyu.codingchallenge.starwars.data.database.dao.MissionsDao
import com.cvitirinyu.codingchallenge.starwars.data.database.entities.StarWarsMission
import com.cvitirinyu.codingchallenge.starwars.utilities.DATABASE_NAME

@Database(entities = [StarWarsMission::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun missionsDao(): MissionsDao

    companion object {

        // For Singleton Instantiation
        @Volatile
        private var instance: AppDatabase? = null

        operator fun invoke(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                DATABASE_NAME
            ).build()
    }
}