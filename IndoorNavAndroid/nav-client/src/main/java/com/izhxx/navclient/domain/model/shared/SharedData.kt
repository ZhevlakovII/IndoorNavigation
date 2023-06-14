package com.izhxx.navclient.domain.model.shared

import com.izhxx.navcore.domain.model.location.Location
import java.io.Serializable

internal class SharedData(
    var locationList: List<Location>? = null,
    var isSetDestination: Boolean = true,
    var currentUserPosition: Location? = null,
    var destination: Location? = null,
    var route: List<Location>? = null
) : Serializable