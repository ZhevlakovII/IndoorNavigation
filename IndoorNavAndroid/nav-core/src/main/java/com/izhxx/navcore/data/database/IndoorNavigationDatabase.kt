package com.izhxx.navcore.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.izhxx.navcore.data.dao.LocationDao
import com.izhxx.navcore.data.dao.SearchDao
import com.izhxx.navcore.data.dao.SettingsDao
import com.izhxx.navcore.data.model.LocationEntity
import com.izhxx.navcore.data.model.SearchRequestEntity
import com.izhxx.navcore.data.model.UserSettingsEntity

@Database(
    entities = [LocationEntity::class, SearchRequestEntity::class, UserSettingsEntity::class],
    version = 1,
    exportSchema = false
)
internal abstract class IndoorNavigationDatabase : RoomDatabase() {

    companion object {
        private const val INDOORNAVIGATION_DATABASE_NAME = "indoornavigation_database"

        @Volatile private var instance: IndoorNavigationDatabase? = null

        fun getInstance(context: Context): IndoorNavigationDatabase {
            return instance ?: synchronized(this) {
                return instance ?: buildDatabase(context).also { instance = it }
            }
        }

        //add worker to move data with json file
        private fun buildDatabase(context: Context): IndoorNavigationDatabase =
            Room.databaseBuilder(
                context,
                IndoorNavigationDatabase::class.java,
                INDOORNAVIGATION_DATABASE_NAME
            ).build()
    }

    abstract fun locationDao(): LocationDao
    abstract fun searchDao(): SearchDao
    abstract fun settingsDao(): SettingsDao

}