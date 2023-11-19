package com.amrhal.custom.customViews;


import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

public class CustomFontTextView extends androidx.appcompat.widget.AppCompatTextView {
    private int typefaceType;
    private Typeface typeface;

    public CustomFontTextView(Context context) {
        super(context);
        init(context);
    }

    public CustomFontTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CustomFontTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public CustomFontTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr,
                              int defStyleRes) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

        initTypeface(context);
    }

    private void init(Context context) {
        initTypeface(context);
    }

    private void initTypeface(Context context) {
        //You logic to use the variable typefaceType and accordingly set the typeface
        super.setTypeface(typeface);
    }
}