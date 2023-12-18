package com.izhxx.navshared.base

import androidx.annotation.CallSuper
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class NavViewModel : ViewModel(), DefaultLifecycleObserver {

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

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        viewModelDisposable.cancel()
    }

}