package com.izhxx.navuikit.ext

import android.content.Context
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.core.content.ContextCompat

fun Context.color(@ColorRes colorId: Int) = ContextCompat.getColor(this, colorId)

fun Context.dimens(@DimenRes dimenId: Int): Int = resources.getDimension(dimenId).toInt()