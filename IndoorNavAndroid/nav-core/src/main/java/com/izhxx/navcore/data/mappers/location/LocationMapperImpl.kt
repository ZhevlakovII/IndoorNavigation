package com.izhxx.navcore.data.mappers.location

import com.izhxx.navcore.data.model.LocationEntity
import com.izhxx.navcore.domain.model.location.Location
import com.izhxx.navcore.domain.model.location.MapCoordinates
import com.izhxx.navcore.utils.LocationsDomain
import com.izhxx.navcore.utils.LocationsEntity

internal class LocationMapperImpl : LocationMapper {
    override fun toEntity(location: Location): LocationEntity {
        location.apply {
            return LocationEntity(
                locationId,
                locationName,
                coordinates.startAxisX,
                coordinates.centerAxisX,
                coordinates.endAxisX,
                coordinates.startAxisY,
                coordinates.centerAxisY,
                coordinates.endAxisY
            )
        }
    }

    override fun toEntity(locations: LocationsDomain): LocationsEntity =
        locations.map { toEntity(it) }

    override fun toModel(locations: LocationsEntity): LocationsDomain =
        locations.map { toModel(it) }

    override fun toModel(location: LocationEntity): Location {
        location.apply {
            val coordinates = MapCoordinates(
                startAxisX,
                centerAxisX,
                endAxisX,
                startAxisY,
                centerAxisY,
                endAxisY
            )

            return Location(
                locationId,
                locationName,
                coordinates
            )
        }
    }

}