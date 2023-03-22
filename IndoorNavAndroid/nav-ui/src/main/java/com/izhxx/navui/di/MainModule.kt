package com.izhxx.navui.di

import com.izhxx.navui.di.usecases.UseCasesModule
import com.izhxx.navui.di.viewmodels.ViewModelModule
import dagger.Module

@Module(includes = [UseCasesModule::class, ViewModelModule::class])
internal interface MainModule