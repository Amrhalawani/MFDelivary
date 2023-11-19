package com.amrhal.custom.customViews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.*

import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import com.amrhal.custom.R
import com.amrhal.mystorematgary.views.customViews.IViews


class CSpinnerView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs), IViews {


    private val tv: TextView
    private val listView: ListView
    var list: MutableList<String>
    var lastSelectedPos = -1 //todo we need to make listener to listen on listview pos
    val adaptor: ArrayAdapter<String>
    var listener: OnSelectedListener? = null

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.custom_spinner, this, true)
        tv = view.findViewById(R.id.text_spinner)
        listView = view.findViewById(R.id.lv_items)
        list = arrayListOf()
        adaptor = ArrayAdapter<String>(
            context,
            R.layout.item_attr, R.id.text_attr_name, list
        )

        listView.adapter = adaptor

        listView.onItemClickListener =
            AdapterView.OnItemClickListener { adapterView, view, pos, l ->

                tv.text = list[pos]
                hideListView()
                lastSelectedPos = pos
                listener?.onListViewItemClick(pos)
            }
        tv.setOnClickListener {
            hideKeyboard()
            showListView()
            performClick()
        }

    }

    override fun setOnClickListener(l: OnClickListener?) {
        super.setOnClickListener(l)
    }

    fun setViewHeightDependOnChildren(listView: ListView) {
        val listAdapter = listView.adapter ?: return
        var totalHeight = listView.paddingTop + listView.paddingBottom
        for (i in 0 until listAdapter.count) {
            val listItem = listAdapter.getView(i, null, listView)
            // (listItem as ViewGroup)?.layoutParams =LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT)

            listItem.measure(0, MeasureSpec.UNSPECIFIED)
            totalHeight += listItem.measuredHeight
        }
        val params = listView.layoutParams
        params.height = totalHeight + listView.dividerHeight * (listAdapter.count - 1)
    }


    fun setHintName(newHint: String) {
        tv.hint = newHint
    }

    fun hideListView() {
        listView.visibility = View.GONE
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return if (event.action == MotionEvent.ACTION_UP) {
            performClick()
        } else true
    }

    fun showListView() {
        listView.visibility = View.VISIBLE
    }

    fun updateList(newList: MutableList<String>) {
        adaptor.clear()
        adaptor.addAll(newList)
        tv.text = ""
        lastSelectedPos = -1 //return lastSelected to original status
        setViewHeightDependOnChildren(listView)
    }


    override fun validate(): Boolean {
        if (lastSelectedPos < 0) {
            validateActions(false)
            return false
        }
        return true

    }

    override fun validateActions(isValid: Boolean) {
        tv.error = "Not Selected yet"
    }

    interface OnSelectedListener {
        fun onListViewItemClick(pos: Int)

    }

    fun setOnSelectedListener(listener: OnSelectedListener) {
        this.listener = listener
    }
    fun hideKeyboard(){
        // Check if no view has focus:
        val view = this
        view?.let { v ->
            val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(v.windowToken, 0)
        }
    }
}