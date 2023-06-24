package com.izhxx.navuikit

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.izhxx.navuikit.databinding.NavUikitPointsInputBinding
import com.izhxx.navuikit.ext.color
import com.izhxx.navuikit.ext.dimens

class NavUiKitPointsInput : ConstraintLayout {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        readAttributes(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        readAttributes(attrs)
    }

    var currentPositionButtonText: String
        get() = binding.navUiKitPointsInputButtonSelectCurrentPosition.text.toString()
        set(value) { binding.navUiKitPointsInputButtonSelectCurrentPosition.text = value }
    var destinationButtonText: String
        get() = binding.navUiKitPointsInputButtonSelectDestination.text.toString()
        set(value) { binding.navUiKitPointsInputButtonSelectDestination.text = value }

    private val binding: NavUikitPointsInputBinding =
        NavUikitPointsInputBinding.inflate(LayoutInflater.from(context), this)

    private fun readAttributes(attrs: AttributeSet) {
        val attrsArray = context.obtainStyledAttributes(attrs, R.styleable.NavUiKitPointsInput)

        try {
            currentPositionButtonText =
                attrsArray.getString(R.styleable.NavUiKitPointsInput_currentPositionHint) ?: ""
            destinationButtonText =
                attrsArray.getString(R.styleable.NavUiKitPointsInput_destinationHint) ?: ""
        } finally {
            attrsArray.recycle()
        }
    }

    fun setBackButtonClickListener(l: OnClickListener?) =
        binding.navUiKitPointsInputButtonBack.setOnClickListener(l)

    fun setCurrentPositionButtonClickListener(l: OnClickListener?) =
        binding.navUiKitPointsInputButtonSelectCurrentPosition.setOnClickListener(l)

    fun setDestinationButtonClickListener(l: OnClickListener?) =
        binding.navUiKitPointsInputButtonSelectDestination.setOnClickListener(l)
}