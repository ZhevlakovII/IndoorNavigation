package com.izhxx.navcreator

import android.content.Context
import android.content.Intent
import com.izhxx.navcore.IndoorNavigationCore
import com.izhxx.navcreator.di.ApplicationComponent
import com.izhxx.navcreator.di.DaggerApplicationComponent
import com.izhxx.navcreator.presentation.CreatorActivity

class IndoorNavigationCreator {

    companion object {
        private var instance: IndoorNavigationCreator? = null
        private lateinit var daggerComponent: ApplicationComponent

        @JvmStatic
        internal fun getDaggerComponent(): ApplicationComponent = daggerComponent

        @JvmStatic
        fun startIndoorNavigationActivity(context: Context) {
            instance = IndoorNavigationCreator()
            setup(context = context)

            val startIntent = Intent(context, CreatorActivity::class.java).apply {
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