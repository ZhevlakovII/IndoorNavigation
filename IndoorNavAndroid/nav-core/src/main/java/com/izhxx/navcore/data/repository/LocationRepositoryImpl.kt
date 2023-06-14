package com.izhxx.navcore.data.repository

import com.izhxx.navcore.data.dao.LocationDao
import com.izhxx.navcore.data.mappers.location.LocationMapper
import com.izhxx.navcore.domain.model.location.Location
import com.izhxx.navcore.domain.repository.LocationRepository
import com.izhxx.navcore.utils.LocationsDomain
import io.reactivex.Single

internal class LocationRepositoryImpl(
    private val locationDao: LocationDao,
    private val locationMapper: LocationMapper
) : LocationRepository {
    override fun insertLocation(location: Location) =
        locationDao.insertLocation(locationMapper.toEntity(location))

    override fun insertLocations(locationsList: List<Location>) =
        locationDao.insertLocations(locationMapper.toEntity(locationsList))


    override fun getLocations(): Single<LocationsDomain> =
        locationDao.getLocations().map { locationMapper.toModel(it) }

    override fun getLocationById(locationId: Int): Single<Location> =
        locationDao.getLocationById(locationId).map { locationMapper.toModel(it) }

    override fun findLocationByOrdinates(x: Double, y: Double): Single<Location> =
        locationDao.findLocationByOrdinates(x, y).map { locationMapper.toModel(it) }

    override fun findLocationByName(name: String): Single<LocationsDomain> =
        locationDao.findLocationByName(name).map { locationMapper.toModel(it) }

}