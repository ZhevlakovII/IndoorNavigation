package com.izhxx.indoornavarandroid

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.izhxx.navui.IndoorNavigationUi

@SuppressLint("CustomSplashScreen")
class SplashActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_IndoorNavigation_Splash)

        IndoorNavigationUi.startIndoorNavigationActivity(this)
    }
}