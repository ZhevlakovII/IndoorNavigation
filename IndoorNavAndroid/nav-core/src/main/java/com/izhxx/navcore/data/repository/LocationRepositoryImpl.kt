package com.izhxx.navcore.data.repository

import com.izhxx.navcore.data.dao.LocationDao
import com.izhxx.navcore.data.mappers.location.LocationMapper
import com.izhxx.navcore.domain.model.location.Location
import com.izhxx.navcore.domain.repository.LocationRepository
import io.reactivex.Single

internal class LocationRepositoryImpl(
    private val locationDao: LocationDao,
    private val locationMapper: LocationMapper
) : LocationRepository {
    override fun insertLocation(location: Location) =
        locationDao.insertLocation(locationMapper.toEntity(location))

    override fun insertLocations(locationsList: List<Location>) =
        locationDao.insertLocations(locationMapper.toEntity(locationsList))


    override fun getLocations(): Single<List<Location>> =
        locationDao.getLocations().map { locationMapper.toModel(it) }

}