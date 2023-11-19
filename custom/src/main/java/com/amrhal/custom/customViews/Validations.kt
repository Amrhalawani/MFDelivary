package com.amrhal.custom.customViews

import com.amrhal.mystorematgary.views.customViews.IViews


/**
 * Created by amrhalawani on 27,March,2021
 */

public fun validations(editViews:Array<IViews>): Boolean {

    for (v in editViews) {
        if (!v.validate()) {
            return false
        }
    }
    return true
}
