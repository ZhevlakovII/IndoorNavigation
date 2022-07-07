package com.izhxx.indoornavarandroid.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel: ViewModel() {
    val cardState = MutableLiveData<Boolean>().apply { value = false }

    val pickedLocationId = MutableLiveData<Int>().apply { value = -1 }

    fun changeCardState(state: Boolean) {
        cardState.value = state
    }

    fun changePickedLocationId(id: Int) {
        pickedLocationId.value = id
    }
}