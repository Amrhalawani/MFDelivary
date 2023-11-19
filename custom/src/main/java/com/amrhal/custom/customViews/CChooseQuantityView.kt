package com.amrhal.custom.customViews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.*
import com.amrhal.custom.R
import kotlinx.android.synthetic.main.layout_choose_quntity.view.*


class CChooseQuantityView : LinearLayout {
//    var tv: TextView? = null
 var qty:Int = 1

    var listener: OnSelectedListener? = null

    constructor(context: Context) : super(context, null)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )


    init {
        val inflater = (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
        val view = inflater.inflate(R.layout.layout_choose_quntity, this, true)

        text_quantity_quantity.text = qty.toString()
        btn_add_one_quantity.setOnClickListener {
            if (qty < 100) {
                qty++
                text_quantity_quantity.text = qty.toString()
            }
            listener?.onQTYItemChange(qty)
        }
        btn_subtract_one_quantity.setOnClickListener {
            if (qty > 1) {
                qty--
                text_quantity_quantity.text = qty.toString()
                listener?.onQTYItemChange(qty)
            }
        }
    }

    interface OnSelectedListener {
        fun onQTYItemChange(qty: Int)

    }

    fun setOnChangeListener(listener: OnSelectedListener) {
        this.listener = listener
    }

//    override fun validate(): Boolean {
//      return validateText()
//    }

//    private fun validateText(): Boolean {
//        if (et.toString().length <= 2) {
//            validateActions(false)
//            return false
//        }
//        return true
//    }
//
//
//    override fun validateActions(isValid: Boolean) {
//        if (!isValid) {
//            et?.error = context.getString(R.string.fill_error_msg)
//        }
//
//    }

    fun getQuentity(): Int? {
        return text_quantity_quantity?.text.toString().toInt()
    }

    fun setQuentity(newQty: Int) {
        qty = newQty
         text_quantity_quantity?.text= qty.toString()
    }

    override fun setOnClickListener(l: OnClickListener?) {
        super.setOnClickListener(l)

    }
}