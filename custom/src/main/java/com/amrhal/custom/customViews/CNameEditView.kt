package com.amrhal.custom.customViews

import android.content.Context
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


class CNameEditView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs), IViews {


    public val et: EditText
    public val tvError: TextView

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.custom_name_address_edit_text_view, this, true)
        et = view.findViewById(R.id.edit_text)
        tvError = view.findViewById(R.id.text_error)

        et.hint = ""
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

    fun setText(text:String) {
        et.setText(text)
    }

    fun setHintName(newHint: String ) {
        et.hint = newHint
    }


    override fun validate(): Boolean {
        if (et.text.toString().length <= 2) {
            validateActions(false)
            return false
        }
        return true
    }

    override fun validateActions(isValid: Boolean) {
        tvError.visibility = View.VISIBLE
        tvError.text = context.getString(R.string.fill_name_error_msg)
        et.background = context.getDrawable(R.drawable.background_squere_fillet10_stroke_error)
    }


}