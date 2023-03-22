package com.izhxx.navuikit.ext

import android.content.Context
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

fun Context.color(@ColorRes colorId: Int) = ContextCompat.getColor(this, colorId)