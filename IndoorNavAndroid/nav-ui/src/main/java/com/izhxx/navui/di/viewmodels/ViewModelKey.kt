package com.izhxx.navui.di.viewmodels

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

@MapKey
internal annotation class ViewModelKey(val viewModelClass: KClass<out ViewModel>)