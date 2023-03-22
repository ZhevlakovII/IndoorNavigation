package com.izhxx.navuikit

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.izhxx.navuikit.databinding.NavUiKitCustomEditTextBinding

class NavUiKitCustomEditText : ConstraintLayout {

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

    var editTextHint: String
        get() = binding.navUiKitCustomEditTextEditText.hint.toString()
        set(value) { binding.navUiKitCustomEditTextEditText.setHint(value) }
    var editTextText: String
        get() = binding.navUiKitCustomEditTextEditText.text.toString()
        set(value) { binding.navUiKitCustomEditTextEditText.setText(value) }

    private val binding: NavUiKitCustomEditTextBinding =
        NavUiKitCustomEditTextBinding.inflate(LayoutInflater.from(context), this)

    private fun readAttributes(attrs: AttributeSet) {
        val attrsArray = context.obtainStyledAttributes(attrs, R.styleable.NavUiKitCustomEditText)

        try {
            editTextHint =
                attrsArray.getString(R.styleable.NavUiKitCustomEditText_customEditTextHint) ?: ""
            editTextText =
                attrsArray.getString(R.styleable.NavUiKitCustomEditText_customEditTextText) ?: ""
        } finally {
            attrsArray.recycle()
        }
    }

    override fun setOnClickListener(l: OnClickListener?) =
        binding.navUiKitCustomEditTextImageButton.setOnClickListener(l)

}