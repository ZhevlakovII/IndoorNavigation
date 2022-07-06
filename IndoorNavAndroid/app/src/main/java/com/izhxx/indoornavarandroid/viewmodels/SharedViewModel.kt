package com.izhxx.indoornavarandroid.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    val cardState = MutableLiveData<Boolean>().apply { value = false }

    fun changeCardState(state: Boolean) {
        cardState.value = state
    }
}