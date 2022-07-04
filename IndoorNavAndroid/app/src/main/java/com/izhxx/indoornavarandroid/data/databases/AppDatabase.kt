package com.izhxx.indoornavarandroid.data.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.izhxx.indoornavarandroid.data.databases.locationsdatabase.Location
import com.izhxx.indoornavarandroid.data.databases.locationsdatabase.LocationDao
import com.izhxx.indoornavarandroid.data.databases.searchhistorydatabase.SearchHistory
import com.izhxx.indoornavarandroid.data.databases.searchhistorydatabase.SearchHistoryDao
import com.izhxx.indoornavarandroid.utilites.DATABASE_NAME
import com.izhxx.indoornavarandroid.utilites.JSON_DATA_FILENAME
import com.izhxx.indoornavarandroid.utilites.workers.DatabaseWorker
import com.izhxx.indoornavarandroid.utilites.workers.DatabaseWorker.Companion.FILENAME

@Database(entities = [Location::class, SearchHistory::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun locationDao(): LocationDao
    abstract fun searchHistoryDao(): SearchHistoryDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                return instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .addCallback(
                    object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            val request = OneTimeWorkRequestBuilder<DatabaseWorker>()
                                .setInputData(workDataOf(FILENAME to JSON_DATA_FILENAME))
                                .build()
                            WorkManager.getInstance(context).enqueue(request)
                        }
                    }
                )
                .build()
        }
    }
}