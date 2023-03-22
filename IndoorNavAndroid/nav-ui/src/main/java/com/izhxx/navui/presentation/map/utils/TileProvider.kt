package com.izhxx.navui.presentation.map.utils

import ovh.plrapps.mapcompose.core.TileStreamProvider
import java.io.IOException

internal fun mapProvider() = TileStreamProvider { row, col, zoom ->
    try {
        //Set map files
//        IndoorNavigationUiApplication.getInstance().applicationContext.assets.open("")
        null
    } catch (io: IOException) {
        io.printStackTrace()
        null
    }
}