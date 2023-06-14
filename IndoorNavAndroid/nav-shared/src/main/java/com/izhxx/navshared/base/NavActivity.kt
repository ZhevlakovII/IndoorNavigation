package com.izhxx.navshared.base

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData

abstract class NavActivity : AppCompatActivity {
    constructor() : super()
    constructor(@LayoutRes contentLayoutId: Int) : super(contentLayoutId)

    protected fun <T> LiveData<T>.observe(block: (T) -> Unit) =
        observe(this@NavActivity) { it?.let(block) }

}