package com.izhxx.navuikit

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.izhxx.navuikit.databinding.NavUiKitButtonSearchBinding

class NavUiKitButtonSearch : ConstraintLayout {

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

    var buttonHint: String
        get() = binding.navUiKitButtonSearchButton.hint.toString()
        set(value) {
            binding.navUiKitButtonSearchButton.hint = value
        }
    var buttonText: String
        get() = binding.navUiKitButtonSearchButton.text.toString()
        set(value) {
            binding.navUiKitButtonSearchButton.text = value
        }

    private val binding: NavUiKitButtonSearchBinding =
        NavUiKitButtonSearchBinding.inflate(LayoutInflater.from(context), this)

    private fun readAttributes(attrs: AttributeSet) {
        val attrsArray = context.obtainStyledAttributes(attrs, R.styleable.NavUiKitButtonSearch)

        try {
            buttonHint = attrsArray.getString(R.styleable.NavUiKitButtonSearch_searchButtonHint) ?: ""
            buttonText = attrsArray.getString(R.styleable.NavUiKitButtonSearch_searchButtonText) ?: ""

            val buttonIcon = attrsArray.getDrawable(R.styleable.NavUiKitButtonSearch_searchButtonIcon)
            buttonIcon?.let { binding.navUiKitButtonSearchButton.icon = it }
        } finally {
            attrsArray.recycle()
        }
    }

    override fun setOnClickListener(l: OnClickListener?) =
        binding.navUiKitButtonSearchButton.setOnClickListener(l)
}