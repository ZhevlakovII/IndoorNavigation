package com.izhxx.navui.utils.ext

import android.content.Context
import com.izhxx.navui.IndoorNavigationUi
import com.izhxx.navui.di.ApplicationComponent

internal val Context.daggerComponent: ApplicationComponent
    get() = IndoorNavigationUi.getDaggerComponent()