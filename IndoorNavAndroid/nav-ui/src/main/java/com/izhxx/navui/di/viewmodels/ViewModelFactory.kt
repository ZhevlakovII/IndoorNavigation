package com.izhxx.navui.di.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Reusable
import java.lang.Exception
import java.lang.IllegalArgumentException
import java.lang.RuntimeException
import javax.inject.Inject
import javax.inject.Provider

@Suppress("UNCHECKED_CAST")
@Reusable
internal class ViewModelFactory @Inject constructor(
    private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val creator = creators[modelClass]
            ?: creators.entries.firstOrNull { modelClass.isAssignableFrom(it.key) }?.value
            ?: throw IllegalArgumentException("Unknown ViewModel class: $modelClass")

        try {
            return creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

}