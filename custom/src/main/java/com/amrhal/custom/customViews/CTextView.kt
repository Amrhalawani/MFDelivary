package com.amrhal.mystorematgary.views.customViews

import android.content.Context
import android.util.AttributeSet

import android.widget.EditText
import com.amrhal.custom.R

class CTextView : EditText, IViews {

    constructor(context: Context) : super(context, null)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun validate(): Boolean {
      return validateText()
    }

    private fun validateText(): Boolean {
        if (text.toString().length <= 2) {
            validateActions(false)
            return false
        }
        return true
    }


    override fun validateActions(isValid: Boolean) {
        if (!isValid) {
            error = context.getString(R.string.fill_error_msg)
        }

    }
}