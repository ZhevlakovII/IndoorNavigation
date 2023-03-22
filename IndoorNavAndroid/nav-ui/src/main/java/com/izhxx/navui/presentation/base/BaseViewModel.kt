package com.izhxx.navui.presentation.base

import androidx.annotation.CallSuper
import androidx.lifecycle.*
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

internal abstract class BaseViewModel : ViewModel(), DefaultLifecycleObserver {

    private val viewModelDisposable = CompositeDisposable()

    protected fun Disposable.untilCleared(): Disposable {
        viewModelDisposable.add(this)
        return this
    }

    protected fun Disposable.cancel() {
        viewModelDisposable.remove(this)
    }

    @CallSuper
    override fun onCleared() {
        viewModelDisposable.clear()
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        onCleared()
    }

}