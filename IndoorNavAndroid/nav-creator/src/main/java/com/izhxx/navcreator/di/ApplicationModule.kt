package com.izhxx.navcreator.di

import com.izhxx.navcreator.di.viewmodels.ViewModelModule
import dagger.Module

@Module(includes = [ViewModelModule::class])
internal interface ApplicationModule