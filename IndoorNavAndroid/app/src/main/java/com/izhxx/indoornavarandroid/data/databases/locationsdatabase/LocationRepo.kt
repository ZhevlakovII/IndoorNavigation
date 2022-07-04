package com.izhxx.indoornavarandroid.data.databases.locationsdatabase

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocationRepo @Inject constructor(
    private val locationDao: LocationDao
) {
    suspend fun getAllLocations() = locationDao.getAllLocations()

    suspend fun getLocationById(id: Int) = locationDao.getLocationById(id)

    suspend fun getLocationName(name: String) = locationDao.getLocationName(name)

    suspend fun getRuLocationName(name: String) = locationDao.getRuLocationName(name)

    suspend fun getLocationCenterX(id: Int) = locationDao.getLocationCenterX(id)
    suspend fun getLocationCenterY(id: Int) = locationDao.getLocationCenterY(id)
}