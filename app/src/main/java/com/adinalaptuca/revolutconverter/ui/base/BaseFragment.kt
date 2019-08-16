package com.adinalaptuca.revolutconverter.ui.base

import android.content.Context
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.adinalaptuca.revolutconverter.R
import com.adinalaptuca.revolutconverter.ui.CustomDialog
import dagger.android.support.AndroidSupportInjection

/**
 * Created by adinalaptuca on Aug, 2019
 */
abstract class BaseFragment<out T : BaseMvp.Presenter> : Fragment(), BaseMvp.View {

    protected abstract fun presenter(): T
    override var isOverridingBack: Boolean = false
    private var isNoInternetDialogDisplayed = false


    // TODO: no internet custom dialog and custom toast!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDI()
        presenter().attach(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    private fun performDI() = AndroidSupportInjection.inject(this)

    @Nullable
    override fun onCreateView(inflater: LayoutInflater, @Nullable container: ViewGroup?, @Nullable savedInstanceState: Bundle?): View? {
        retainInstance = true
        return inflater.inflate(layout(), container, false)
    }

    @LayoutRes
    protected abstract fun layout(): Int

    override fun onDestroy() {
        super.onDestroy()
        presenter().detach()
    }

    override fun onDetach() {
        super.onDetach()
        presenter().detach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter().detach()
    }

    override fun onResume() {
        super.onResume()
        presenter().attach(this)
    }

    override fun onBackTriggered() {

    }

    override fun showActionNotAvailableInOfflineMode() {
        if (!isNoInternetDialogDisplayed) {
            isNoInternetDialogDisplayed = true
            context?.let {
                val actionInOfflineNotAvailableDialog = CustomDialog(it)
                actionInOfflineNotAvailableDialog.setPositiveButtonListener(object : CustomDialog.SaveClickListener {
                    override fun save() {}
                })
                actionInOfflineNotAvailableDialog.showDialog(resources.getString(R.string.wifi_signal),
                    resources.getString(R.string.no_internet_connection_message), resources.getString(R.string.ok_button))
                actionInOfflineNotAvailableDialog.setDismissListener(object : CustomDialog.DismissListener {
                    override fun dismiss() {
                        isNoInternetDialogDisplayed = false
                    }
                })
            }
        }
    }

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

}
