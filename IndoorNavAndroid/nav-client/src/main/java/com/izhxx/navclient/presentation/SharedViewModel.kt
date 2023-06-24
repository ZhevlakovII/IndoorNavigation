package com.izhxx.navclient.presentation

import androidx.lifecycle.LiveData
import com.izhxx.navclient.domain.model.error.ErrorModel

internal interface SharedViewModel {

    val error: LiveData<ErrorModel>

    fun fetchLocations()
}