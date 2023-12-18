package com.izhxx.navshared.utils

import android.content.Context
import ovh.plrapps.mapcompose.core.TileStreamProvider
import java.io.IOException

fun getTileStreamProvider(context: Context) = TileStreamProvider { row, col, zoom ->
    try {
        context.assets.open("mapAssets/$zoom/$row/$col.png")
    } catch (e: IOException) {
        e.printStackTrace()
        null
    }
}