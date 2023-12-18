package com.izhxx.navuikit

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import androidx.annotation.DrawableRes
import androidx.core.view.updateLayoutParams
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.izhxx.navuikit.databinding.NavUikitToastBinding
import com.izhxx.navuikit.ext.dimens

class NavUiKitToast private constructor() {

    companion object {
        const val DURATION_SHORT = 1000L
        const val DURATION_MEDIUM = 2000L
        const val DURATION_LONG = 4000L

        private fun build(
            rootView: View,
            duration: Long,
            message: String,
            @DrawableRes icon: Int
        ) {
            val toast = Snackbar.make(rootView, "", BaseTransientBottomBar.LENGTH_INDEFINITE)
            val toastView = NavUikitToastBinding.inflate(LayoutInflater.from(rootView.context))

            toastView.navUiKitToastTextViewMessage.text = message
            toastView.navUiKitToastImageViewIcon.setImageResource(icon)

            toast.apply {
                this.duration = duration.toInt()
                animationMode = BaseTransientBottomBar.ANIMATION_MODE_SLIDE
                with(view as ViewGroup) {
                    addView(toastView.root, 0)
                    setBackgroundColor(Color.TRANSPARENT)
                    updateLayoutParams<MarginLayoutParams> {
                        setMargins(
                            leftMargin,
                            topMargin,
                            rightMargin,
                            context.dimens(R.dimen.nav_uikit_default_medium_margin)
                        )
                    }
                }
                show()
            }
        }
    }

    open class Builder(private val rootView: View) {

        private var duration: Long = DURATION_SHORT
        private var message: String = String()
        @DrawableRes protected open var iconResource: Int = 0

        fun duration(duration: Long): Builder {
            this.duration = duration
            return this
        }
        fun message(message: String): Builder {
            this.message = message
            return this
        }
        fun drawable(@DrawableRes drawableRes: Int): Builder {
            this.iconResource = drawableRes
            return this
        }
        fun show() = build(
            duration = duration,
            message = message,
            icon = iconResource,
            rootView = rootView
        )

        fun success(): Builder = SuccessToast(rootView)
        fun warning(): Builder = WarningToast(rootView)
        fun error(): Builder = ErrorToast(rootView)
        fun info(): Builder = InfoToast(rootView)

        internal inner class SuccessToast(rootView: View) : Builder(rootView) {
            override var iconResource: Int = R.drawable.nav_uikit_ic_success
        }
        internal inner class WarningToast(rootView: View) : Builder(rootView) {
            override var iconResource: Int = R.drawable.nav_uikit_ic_warning
        }
        internal inner class ErrorToast(rootView: View) : Builder(rootView) {
            override var iconResource: Int = R.drawable.nav_uikit_ic_error
        }
        internal inner class InfoToast(rootView: View) : Builder(rootView) {
            override var iconResource: Int = R.drawable.nav_uikit_ic_info
        }
    }
}