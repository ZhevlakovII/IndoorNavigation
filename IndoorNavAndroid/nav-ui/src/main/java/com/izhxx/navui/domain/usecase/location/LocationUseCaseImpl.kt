package com.izhxx.navui.domain.usecase.location

import com.izhxx.navcore.domain.model.location.Location
import com.izhxx.navcore.domain.repository.LocationRepository
import io.reactivex.Single
import javax.inject.Inject

internal class LocationUseCaseImpl @Inject constructor(
    private val locationRepository: LocationRepository
) : LocationUseCase {
    override fun insertLocation(location: Location) = locationRepository.insertLocation(location)

    override fun insertLocations(locationsList: List<Location>) =
        locationRepository.insertLocations(locationsList)

    override fun getLocations(): Single<List<Location>> = locationRepository.getLocations()

}