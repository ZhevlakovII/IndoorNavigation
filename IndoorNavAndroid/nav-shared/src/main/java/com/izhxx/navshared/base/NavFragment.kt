package com.izhxx.navshared.base

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData

abstract class NavFragment : Fragment {
    constructor() : super()
    constructor(@LayoutRes contentLayoutId: Int) : super(contentLayoutId)

    protected fun <T> LiveData<T>.observe(block: (T) -> Unit) =
        observe(viewLifecycleOwner) { it?.let(block) }

    open fun onBackPressed() {}

    private val backPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() = onBackPressed()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            backPressedCallback
        )
    }

    override fun getView(): View = requireNotNull(super.getView())
    override fun getContext(): Context = requireNotNull(super.getContext())

}