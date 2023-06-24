package com.izhxx.navuikit

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.izhxx.navuikit.databinding.NavUikitCardPointInfoBinding

class NavUiKitCardPointInfo : ConstraintLayout {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) { readAttributes(attrs) }
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        readAttributes(attrs)
    }

    var titleText: String
        get() = binding.navUiKitCardPointInfoTitle.text.toString()
        set(value) {
            binding.navUiKitCardPointInfoTitle.text = value
        }
    var buttonText: String
        get() = binding.navUiKitCardPointInfoButton.text.toString()
        set(value) {
            binding.navUiKitCardPointInfoButton.text = value
        }

    private var binding: NavUikitCardPointInfoBinding =
        NavUikitCardPointInfoBinding.inflate(LayoutInflater.from(context), this)

    private fun readAttributes(attrs: AttributeSet) {
        val attrsArray = context.obtainStyledAttributes(attrs, R.styleable.NavUiKitCardPointInfo)

        try {
            titleText = attrsArray.getString(R.styleable.NavUiKitCardPointInfo_cardPointInfoTitleText) ?: ""
            buttonText = attrsArray.getString(R.styleable.NavUiKitCardPointInfo_cardPointInfoButtonText) ?: ""
            val buttonIcon = attrsArray.getDrawable(R.styleable.NavUiKitCardPointInfo_cardPointInfoButtonIcon)
            binding.navUiKitCardPointInfoButton.iconTint = attrsArray
                .getColorStateList(R.styleable.NavUiKitCardPointInfo_cardPointInfoButtonIconColor)

            buttonIcon?.let { binding.navUiKitCardPointInfoButton.icon = it }
        } finally {
            attrsArray.recycle()
        }
    }

    override fun setOnClickListener(l: OnClickListener?) =
        binding.navUiKitCardPointInfoButton.setOnClickListener(l)

}