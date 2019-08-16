package com.adinalaptuca.revolutconverter.ui.base

/**
 * Created by adinalaptuca on Aug, 2019
 */
interface BaseMvp {
    interface View {
        fun onBackTriggered()
        var isOverridingBack: Boolean
        fun showActionNotAvailableInOfflineMode()
        fun doIfHasInternetConnectivity(doAfter: () -> Unit): Boolean
        fun doIfHasInternetConnectivity(doAfter: () -> Unit, doOnError: () -> Unit): Boolean
    }

    interface Presenter {
        var hasInternetConnection : Boolean
        fun setInitialInternetConnectionValue(hasInternet: Boolean)
        fun attach(view: BaseMvp.View)
        fun detach()
    }
}