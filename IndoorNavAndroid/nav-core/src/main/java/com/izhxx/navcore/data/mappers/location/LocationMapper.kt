package com.izhxx.navcore.data.mappers.location

import com.izhxx.navcore.data.model.LocationEntity
import com.izhxx.navcore.domain.model.location.Location

internal interface LocationMapper {
    fun toEntity(location: Location): LocationEntity

    fun toEntity(locations: List<Location>): List<LocationEntity>

    fun toModel(locations: List<LocationEntity>): List<Location>
}