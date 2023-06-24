package com.izhxx.navclient.utils.ext

import android.content.Context
import com.izhxx.navclient.IndoorNavigationClient
import com.izhxx.navclient.di.ApplicationComponent

internal val Context.daggerComponent: ApplicationComponent
    get() = IndoorNavigationClient.getDaggerComponent()