package com.izhxx.navuikit.ext

import android.app.Activity
import android.view.View
import androidx.fragment.app.Fragment

fun View.changeVisibility(isVisible: Boolean) = if (isVisible) visible() else invisible()


fun View.hide(isHide: Boolean) = if (isHide) gone() else visible()


fun Fragment.changeVisibility(vararg views: View, isVisible: Boolean) {
    for (view in views) view.changeVisibility(isVisible)
}

fun Fragment.hideView(vararg views: View, isHide: Boolean) {
    for (view in views) view.hide(isHide)
}

fun Activity.changeVisibility(vararg views: View, isVisible: Boolean) {
    for (view in views) view.changeVisibility(isVisible)
}

fun Activity.hideView(vararg views: View, isHide: Boolean) {
    for (view in views) view.hide(isHide)
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}