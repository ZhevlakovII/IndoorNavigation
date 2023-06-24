package com.izhxx.navcore.data.mappers.location

import com.izhxx.navcore.data.model.LocationEntity
import com.izhxx.navcore.domain.model.location.Location
import com.izhxx.navcore.utils.LocationsDomain
import com.izhxx.navcore.utils.LocationsEntity

internal interface LocationMapper {
    fun toEntity(location: Location): LocationEntity

    fun toEntity(locations: LocationsDomain): LocationsEntity

    fun toModel(locations: LocationsEntity): LocationsDomain

    fun toModel(location: LocationEntity): Location
}