package com.izhxx.navshared.base

import android.content.Context
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData

abstract class NavFragment : Fragment {
    constructor() : super()
    constructor(@LayoutRes contentLayoutId: Int) : super(contentLayoutId)

    protected fun <T> LiveData<T>.observe(block: (T) -> Unit) =
        observe(viewLifecycleOwner) { it?.let(block) }

    override fun getView(): View = requireNotNull(super.getView()) {
        "$tag view is not inflate or bind"
    }
    override fun getContext(): Context = requireNotNull(super.getContext()) {
        "$tag is not attached on context"
    }

}