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


class CEditTextView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs), IViews {


     val et: EditText
     val text_label: TextView
     val text_label_optional: TextView

     val tvError: TextView



    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.custom_c_edit_text_view, this, true)
        et = view.findViewById(R.id.edit_text)
        text_label = view.findViewById(R.id.text_label)
        text_label_optional = view.findViewById(R.id.text_label_optional)

        tvError = view.findViewById(R.id.text_error)

      //  et.hint = context.getString(R.string.write_complaint_here)
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


        val a: TypedArray = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.c_attr,
            0, 0)
        try {
            text_label.text = a.getString(R.styleable.c_attr_c_title_label)
            et.hint = a.getString(R.styleable.c_attr_c_hint)
            if (a.getBoolean(R.styleable.c_attr_c_optional_sign, false)) {
                text_label_optional.visibility = View.VISIBLE
            }else {
                text_label_optional.visibility = View.GONE
            }

         } finally {
            a.recycle()
        }

    }


//    fun setViewHeightDependOnChildren(listView: ListView) {
//        val listAdapter = listView.adapter ?: return
//        var totalHeight = listView.paddingTop + listView.paddingBottom
//        for (i in 0 until listAdapter.count) {
//            val listItem = listAdapter.getView(i, null, listView)
//            // (listItem as ViewGroup)?.layoutParams =LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT)
//
//            listItem.measure(0, MeasureSpec.UNSPECIFIED)
//            totalHeight += listItem.measuredHeight
//        }
//        val params = listView.layoutParams
//        params.height = totalHeight + listView.dividerHeight * (listAdapter.count - 1)
//    }


    fun getText():String {
    return et.text.toString()
    }
   fun setText(txt:String) {
   et.setText(txt)
    }

    fun setHintName(newHint: String ) {
        et.hint = newHint
    }

fun setLabelName(newText: String ) {
    text_label.setText(newText)
    }


    override fun validate(): Boolean {
        if (et.text.toString().length < 1 ) {
            validateActions(false)
            return false
        }
        return true

    }

    override fun validateActions(isValid: Boolean) {
        tvError.visibility = View.VISIBLE
        tvError.text = context.getString(R.string.must_fill_this_field)
        et.background = context.getDrawable(R.drawable.background_squere_fillet10_stroke_error)
    }


}