package com.izhxx.navcore

import android.content.Context
import com.izhxx.navcore.data.database.IndoorNavigationDatabase
import com.izhxx.navcore.data.mappers.location.LocationMapperImpl
import com.izhxx.navcore.data.mappers.search.SearchMapperImpl
import com.izhxx.navcore.data.mappers.settings.SettingsMapperImpl
import com.izhxx.navcore.data.repository.LocationRepositoryImpl
import com.izhxx.navcore.data.repository.SearchRepositoryImpl
import com.izhxx.navcore.data.repository.SettingsRepositoryImpl
import com.izhxx.navcore.domain.repository.LocationRepository
import com.izhxx.navcore.domain.repository.SearchRepository
import com.izhxx.navcore.domain.repository.SettingsRepository
import com.izhxx.navcore.utils.getNullableCoreMessage

class IndoorNavigationCore {

    companion object {
        private var coreInstance: IndoorNavigationCore? = null

        @JvmStatic
        fun getInstance(): IndoorNavigationCore = requireNotNull(coreInstance) {
            getNullableCoreMessage()
        }

        @JvmStatic
        fun initialize(context: Context) {
            coreInstance = IndoorNavigationCore().configure(context)
        }
    }

    private var locationRepository: LocationRepository? = null
    private var searchRepository: SearchRepository? = null
    private var settingsRepository: SettingsRepository? = null

    private fun configure(context: Context): IndoorNavigationCore {
        val appDatabase = IndoorNavigationDatabase.getInstance(context)

        locationRepository = LocationRepositoryImpl(appDatabase.locationDao(), LocationMapperImpl())
        searchRepository = SearchRepositoryImpl(appDatabase.searchDao(), SearchMapperImpl())
        settingsRepository = SettingsRepositoryImpl(appDatabase.settingsDao(), SettingsMapperImpl())

        return this
    }

    fun getLocationRepository(): LocationRepository = requireNotNull(locationRepository)

    fun getSearchRepository(): SearchRepository = requireNotNull(searchRepository)

    fun getSettingsRepository(): SettingsRepository = requireNotNull(settingsRepository)

}