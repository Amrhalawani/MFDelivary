package com.amrhal.custom.customViews

import android.content.Context
import android.content.res.TypedArray
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isVisible
import com.amrhal.custom.R
import com.amrhal.mystorematgary.views.customViews.IViews
import kotlinx.android.synthetic.main.custom_name_edit_text_with_icon.view.*


class CEmailEditViewWithIcon(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs), IViews {


    public val et: EditText
    public val tvError: TextView

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.custom_email_edit_text_with_icon, this, true)
        et = view.findViewById(R.id.edit_text)
        tvError = view.findViewById(R.id.text_error)

        et.hint = context.getString(R.string.email)
        et.inputType = InputType.TYPE_CLASS_TEXT;

        et.addTextChangedListener(object: TextWatcher {override fun afterTextChanged(s: Editable?) {

        }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                if (tvError.isVisible){
                    tvError.visibility = View.GONE
                    et.background = context.getDrawable(R.drawable.background_squere_fillet12)
                }

            }

        })


        et.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus){
                et.background = context.getDrawable(R.drawable.background_squere_fillet10_stroke_selected)
            }else {
                et.background = context.getDrawable(R.drawable.background_squere_fillet12)
            }
        }

        val a: TypedArray = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.c_attr,
            0, 0
        )
        try {
            et.hint = a.getString(R.styleable.c_attr_c_hint)

            edit_text.setCompoundDrawablesRelativeWithIntrinsicBounds(
                a.getDrawable(R.styleable.c_attr_c_drawable_start),
                null,
                null,
                null
            )


        } finally {
            a.recycle()
        }

    }



    fun getText():String {
    return et.text.toString()
    }

    fun setText(text:String) {
        et.setText(text)
    }



    fun setHintName(newHint: String ) {
        et.hint = newHint
    }


    override fun validate(): Boolean {
        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(et.text.toString()).matches()){
            validateActions(false)
            return false
        }
        return true
    }

    override fun validateActions(isValid: Boolean) {
        tvError.visibility = View.VISIBLE
        tvError.text = context.getString(R.string.email_error_msg)
        et.background = context.getDrawable(R.drawable.background_squere_fillet10_stroke_error)
    }


}