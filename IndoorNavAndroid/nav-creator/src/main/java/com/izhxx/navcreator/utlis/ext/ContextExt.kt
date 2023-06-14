package com.izhxx.navcreator.utlis.ext

import android.content.Context
import com.izhxx.navcreator.IndoorNavigationCreator
import com.izhxx.navcreator.di.ApplicationComponent

internal val Context.daggerComponent: ApplicationComponent
    get() = IndoorNavigationCreator.getDaggerComponent()