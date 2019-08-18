package com.adinalaptuca.revolutconverter.ui.base

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MotionEvent
import android.widget.EditText
import com.adinalaptuca.revolutconverter.utils.Keyboard
import dagger.android.AndroidInjection

/**
 * Created by adinalaptuca on Aug, 2019
 */

abstract class BaseActivity<out T : BaseMvp.Presenter> : AppCompatActivity(), BaseMvp.View {

    protected abstract fun presenter(): T
    private var isDispatchEventActive = true

    override var isOverridingBack: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        performDI()
        super.onCreate(savedInstanceState)
    }

    private fun performDI() = AndroidInjection.inject(this)

    override fun onBackTriggered() {}

    override fun doIfHasInternetConnectivity(doAfter: () -> Unit): Boolean {
        return if (!presenter().hasInternetConnection) {
            showActionNotAvailableInOfflineMode()
            true
        } else {
            doAfter.invoke()
            false
        }
    }
    override fun doIfHasInternetConnectivity(doAfter: () -> Unit, doOnError: () -> Unit): Boolean {
        return if (!presenter().hasInternetConnection) {
            showActionNotAvailableInOfflineMode()
            doOnError.invoke()
            true
        } else {
            doAfter.invoke()
            false
        }
    }

    override fun showActionNotAvailableInOfflineMode() {}

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN && isDispatchEventActive) {
            if (currentFocus is EditText) {
                currentFocus.clearFocus()
                Keyboard.hideKeyboard(this)
            }
        }
        return super.dispatchTouchEvent(event)
    }
}