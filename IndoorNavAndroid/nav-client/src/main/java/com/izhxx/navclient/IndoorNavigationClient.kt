package com.izhxx.navclient

import android.content.Context
import android.content.Intent
import com.izhxx.navcore.IndoorNavigationCore
import com.izhxx.navclient.di.ApplicationComponent
import com.izhxx.navclient.di.DaggerApplicationComponent
import com.izhxx.navclient.presentation.ClientActivity
import com.izhxx.navshared.utils.getTileStreamProvider

class IndoorNavigationClient {

    companion object {
        private var instance: IndoorNavigationClient? = null
        private lateinit var daggerComponent: ApplicationComponent

        @JvmStatic
        internal fun getDaggerComponent(): ApplicationComponent = daggerComponent

        @JvmStatic
        fun startIndoorNavigationActivity(context: Context) {
            instance = IndoorNavigationClient()
            setup(context = context)

            val startIntent = Intent(context, ClientActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
            }
            context.startActivity(startIntent)
        }

        private fun setup(context: Context) {
            IndoorNavigationCore.initialize(context = context)

            daggerComponent = DaggerApplicationComponent
                .builder()
                .bindContext(context = context)
                .bindTileProvider(getTileStreamProvider(context = context))
                .build()
        }
    }

}