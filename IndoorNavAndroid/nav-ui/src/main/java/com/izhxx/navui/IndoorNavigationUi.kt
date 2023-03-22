package com.izhxx.navui

import android.content.Context
import android.content.Intent
import com.izhxx.navcore.IndoorNavigationCore
import com.izhxx.navui.di.ApplicationComponent
import com.izhxx.navui.di.DaggerApplicationComponent
import com.izhxx.navui.presentation.MainActivity

class IndoorNavigationUi {

    companion object {
        private var instance: IndoorNavigationUi? = null
        private lateinit var daggerComponent: ApplicationComponent

        @JvmStatic
        internal fun getDaggerComponent(): ApplicationComponent = daggerComponent

        @JvmStatic
        fun startIndoorNavigationActivity(context: Context) {
            instance = IndoorNavigationUi()
            setup(context = context)

            val startIntent = Intent(context, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
            }
            context.startActivity(startIntent)
        }

        private fun setup(context: Context) {
            IndoorNavigationCore.initialize(context = context)

            daggerComponent = DaggerApplicationComponent
                .builder()
                .bindContext(context = context)
                .build()
        }
    }

}