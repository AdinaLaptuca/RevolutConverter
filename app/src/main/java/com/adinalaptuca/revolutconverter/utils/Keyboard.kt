package com.adinalaptuca.revolutconverter.utils

import android.app.Activity
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager

/**
 * Created by adinalaptuca on Aug, 2019
 */

object Keyboard {

    fun hideKeyboard(context: Activity) {
        context.currentFocus?.let{
            val inputMethodManager = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(context.currentFocus!!.windowToken, 0)
        }
    }

    fun showKeyboard(context: Activity) {
        context.currentFocus?.let{
            val inputMethodManager = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.toggleSoftInput(
                InputMethodManager.SHOW_FORCED or WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE,
                InputMethodManager.HIDE_IMPLICIT_ONLY)
        }
    }
}