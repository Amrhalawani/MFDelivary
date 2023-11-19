package com.amrhal.custom.customViews

import android.content.Context
import android.content.res.Configuration
import android.content.res.TypedArray
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isVisible
import com.amrhal.custom.R
import com.amrhal.mystorematgary.views.customViews.IViews
import kotlinx.android.synthetic.main.custom_name_edit_text_with_icon.view.*


class CPasswordWithIconEditView (context: Context, attrs: AttributeSet) : LinearLayout(context, attrs), IViews {

    var isEyeOn:Boolean = false
    var et: EditText
    var eyeImage:ImageView? = null
    public val tvError: TextView




    init {
        val inflater = (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
        val view = inflater.inflate(R.layout.custom_edit_text_passoword_with_icon, this, true)
        et = view.findViewById<EditText>(R.id.text_password)
        eyeImage = view.findViewById<ImageView>(R.id.image_on_off_eye_password)
        tvError = view.findViewById(R.id.text_error)
        forceTextDirection()

        (eyeImage as ImageView).setOnClickListener {
          if ( !isEyeOn){
             (eyeImage as ImageView).setImageResource(R.drawable.ic_on_eye)
       //    (et as EditText).inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_NORMAL  //doesnt work

             hidePassword()

             isEyeOn=true
         }else{
             (eyeImage as ImageView).setImageResource(R.drawable.ic_off_eye)

             (et as EditText).transformationMethod = PasswordTransformationMethod.getInstance()
             (et as EditText).textDirection = View.TEXT_DIRECTION_LOCALE
             isEyeOn=false
         }

            performClick()
        }

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



        val a: TypedArray = context.theme.obtainStyledAttributes(
            attrs, R.styleable.c_attr,
            0, 0
        )
        try {
         //   edit_text.text = a.getString(R.styleable.c_attr_c_title_label)


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
     fun hidePassword()  {
         (et as EditText).transformationMethod = HideReturnsTransformationMethod.getInstance()
         forceTextDirection()
    }

    fun forceTextDirection(){
        val config: Configuration = resources.configuration
        if (config.layoutDirection == View.LAYOUT_DIRECTION_RTL) {
            (et as EditText).textDirection = View.TEXT_DIRECTION_RTL
        }
    }

    override fun validate(): Boolean {
      return validateText()
    }

    private fun validateText(): Boolean {
        if (et.text.toString().length < 6) {
            validateActions(false)
            return false
        }
        return true
    }


    override fun validateActions(isValid: Boolean) {

        tvError.visibility = View.VISIBLE
        tvError.text = context.getString(R.string.must_add_a_valid_password)
        et.background = context.getDrawable(R.drawable.background_squere_fillet10_stroke_error)

    }
    public fun setValidateActionError(errorMsg: String) {

        tvError.visibility = View.VISIBLE
        tvError.text = errorMsg
        et.background = context.getDrawable(R.drawable.background_squere_fillet10_stroke_error)

    }

    fun getText(): Editable? {
        return et?.text
    }

    fun setHint(hint:String) {
         et.hint = hint
    }
}