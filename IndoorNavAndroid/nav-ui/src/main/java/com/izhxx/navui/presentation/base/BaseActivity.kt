package com.izhxx.navui.presentation.base

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData

internal abstract class BaseActivity : AppCompatActivity {
    constructor() : super()
    constructor(@LayoutRes contentLayoutId: Int) : super(contentLayoutId)

    protected fun <T> LiveData<T>.observe(block: (T) -> Unit) =
        observe(this@BaseActivity) { it?.let(block) }

}