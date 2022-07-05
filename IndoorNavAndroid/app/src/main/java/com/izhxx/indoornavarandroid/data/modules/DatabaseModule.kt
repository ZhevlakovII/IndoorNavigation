package com.izhxx.indoornavarandroid.data.modules

import android.content.Context
import com.izhxx.indoornavarandroid.data.databases.AppDatabase
import com.izhxx.indoornavarandroid.data.databases.locationsdatabase.LocationDao
import com.izhxx.indoornavarandroid.data.databases.searchhistorydatabase.SearchHistoryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    fun provideSearchItemsDao(appDatabase: AppDatabase): SearchHistoryDao {
        return appDatabase.searchHistoryDao()
    }

    @Provides
    fun provideNavLocationsDao(appDatabase: AppDatabase): LocationDao {
        return appDatabase.locationDao()
    }
}