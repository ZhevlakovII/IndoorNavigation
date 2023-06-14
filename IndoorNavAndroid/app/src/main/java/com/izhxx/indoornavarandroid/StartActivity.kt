package com.izhxx.indoornavarandroid

import android.annotation.SuppressLint
import android.os.Bundle
import com.google.android.material.button.MaterialButton
import com.izhxx.navclient.IndoorNavigationClient
import com.izhxx.navcreator.IndoorNavigationCreator
import com.izhxx.navshared.base.NavActivity

@SuppressLint("CustomSplashScreen")
class StartActivity: NavActivity() {

    private lateinit var buttonOpenClient: MaterialButton
    private lateinit var buttonOpenCreator: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTheme(com.izhxx.navuikit.R.style.IndoorNavigation)
        setContentView(R.layout.nav_activity_start)

        buttonOpenClient = findViewById(R.id.navAppButtonOpenClient)
        buttonOpenCreator = findViewById(R.id.navAppButtonOpenCreator)

        initButtons()
    }

    private fun initButtons() {
        buttonOpenClient.setOnClickListener {
            IndoorNavigationClient.startIndoorNavigationActivity(this)
        }
        buttonOpenCreator.setOnClickListener {
            IndoorNavigationCreator.startIndoorNavigationActivity(this)
        }
    }
}