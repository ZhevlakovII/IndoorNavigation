package com.izhxx.navuikit

import android.content.Context
import android.text.Editable
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.textfield.TextInputEditText
import com.izhxx.navuikit.databinding.NavUikitCustomEditTextBinding

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
        set(value) {
            binding.navUiKitCustomEditTextEditText.hint = value
        }
    var editTextText: String
        get() = binding.navUiKitCustomEditTextEditText.text.toString()
        set(value) {
            binding.navUiKitCustomEditTextEditText.setText(value)
        }

    var onCompleteTextChange: ((String) -> Unit)? = null

    private val binding: NavUikitCustomEditTextBinding =
        NavUikitCustomEditTextBinding.inflate(LayoutInflater.from(context), this)

    init {
        initEditText()
    }

    private fun initEditText() {
        getEditText().doAfterTextChanged { text: Editable? -> onCompleteTextChange?.invoke(text.toString()) }
    }

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

    fun getEditText(): TextInputEditText {
        return binding.navUiKitCustomEditTextEditText
    }

}