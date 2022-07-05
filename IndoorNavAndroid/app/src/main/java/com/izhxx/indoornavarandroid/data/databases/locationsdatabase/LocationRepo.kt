package com.izhxx.indoornavarandroid.data.databases.locationsdatabase

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocationRepo @Inject constructor(
    private val locationDao: LocationDao
) {
    fun getAllLocations() = locationDao.getAllLocations()

    fun getLocationById(id: Int) = locationDao.getLocationById(id)

    fun getLocationName(name: String) = locationDao.getLocationName(name)

    fun getRuLocationName(name: String) = locationDao.getRuLocationName(name)

    fun getLocationCenterX(id: Int) = locationDao.getLocationCenterX(id)
    fun getLocationCenterY(id: Int) = locationDao.getLocationCenterY(id)
}