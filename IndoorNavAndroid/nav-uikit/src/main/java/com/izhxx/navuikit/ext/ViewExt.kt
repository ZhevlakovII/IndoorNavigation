package com.izhxx.navuikit.ext

import android.app.Activity
import android.view.View
import androidx.fragment.app.Fragment

fun View.changeVisibility(isVisible: Boolean) {
    this.visibility = if (isVisible) View.VISIBLE else View.GONE
}

fun Fragment.changeVisibility(vararg views: View, isVisible: Boolean) {
    for (view in views) view.changeVisibility(isVisible)
}

fun Activity.changeVisibility(vararg views: View, isVisible: Boolean) {
    for (view in views) view.changeVisibility(isVisible)
}