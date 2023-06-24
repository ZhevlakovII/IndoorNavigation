package com.izhxx.navshared.dagger

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

@MapKey
annotation class NavDaggerViewModelKey(val viewModelClass: KClass<out ViewModel>)