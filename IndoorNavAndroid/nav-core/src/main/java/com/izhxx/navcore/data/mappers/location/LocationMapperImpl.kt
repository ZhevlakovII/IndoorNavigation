package com.izhxx.navcore.data.mappers.location

import com.izhxx.navcore.data.model.LocationEntity
import com.izhxx.navcore.domain.model.location.Location
import com.izhxx.navcore.domain.model.location.MapCoordinates

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

    override fun toEntity(locations: List<Location>): List<LocationEntity> =
        locations.map { toEntity(it) }

    override fun toModel(locations: List<LocationEntity>): List<Location> =
        locations.map {
            val coordinates =
                MapCoordinates(
                    it.startAxisX,
                    it.centerAxisX,
                    it.endAxisX,
                    it.startAxisY,
                    it.centerAxisY,
                    it.endAxisY
                )

            Location(
                it.locationId,
                it.locationName,
                coordinates
            )
        }

}