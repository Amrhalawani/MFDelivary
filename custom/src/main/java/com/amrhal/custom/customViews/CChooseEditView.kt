package com.amrhal.custom.customViews

import android.content.Context
import android.content.res.TypedArray
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.*

import androidx.core.view.isVisible
import com.amrhal.custom.R
import com.amrhal.mystorematgary.views.customViews.IViews


class CChooseTextView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs), IViews {


     val tv: TextView
     val tvError: TextView

     var isUserChooseSomeThing:Boolean = false

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.custom_choose_text_with_icon_and_arrow, this, true)
        tv = view.findViewById(R.id.text)
        tvError = view.findViewById(R.id.text_error)


        tv.inputType = InputType.TYPE_CLASS_TEXT;

        tv.addTextChangedListener(object: TextWatcher {override fun afterTextChanged(s: Editable?) {

        }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                if (tvError.isVisible){
                    tvError.visibility = View.GONE
                    tv.background = context.getDrawable(R.drawable.background_squere_fillet12)
                }

            }

        })


        val a: TypedArray = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.c_attr,
            0, 0)
        try {
            tv.text = a.getString(R.styleable.c_attr_c_hint)
        } finally {
            a.recycle()
        }

    }



    fun getText():String {
    return tv.text.toString()
    }

    fun setText(text:String) {
        tv.setText(text)
        isUserChooseSomeThing = true
    }


    fun setHintName(newHint: String ) {
        tv.hint = newHint
    }

    override fun validate(): Boolean {
        if (!isUserChooseSomeThing) {
            validateActions(false)
            return false
        }
        return true
    }

    override fun validateActions(isValid: Boolean) {
        tvError.visibility = View.VISIBLE
        tvError.text = context.getString(R.string.must_choose_to_continue)
        tv.background = context.getDrawable(R.drawable.background_squere_fillet10_stroke_error)
    }


//    override fun validate(): Boolean {
//        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(et.text.toString()).matches()){
//            validateActions(false)
//            return false
//        }
//        return true
//    }
//
//    override fun validateActions(isValid: Boolean) {
//        tvError.visibility = View.VISIBLE
//        tvError.text = context.getString(R.string.email_error_msg)
//        et.background = context.getDrawable(R.drawable.background_squere_fillet10_stroke_error)
//    }


}