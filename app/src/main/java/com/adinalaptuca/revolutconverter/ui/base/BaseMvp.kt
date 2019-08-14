package com.adinalaptuca.revolutconverter.ui.base

/**
 * Created by adinalaptuca on Aug, 2019
 */
interface BaseMvp {
    interface View {
        fun onBackTriggered()
        var isOverridingBack: Boolean
    }

    interface Presenter {
        var hasInternetConnection : Boolean
        fun setInitialInternetConnectionValue(hasInternet: Boolean)
        fun attach(view: BaseMvp.View)
        fun detach()
    }
}