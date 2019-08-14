package com.adinalaptuca.revolutconverter.ui.splash

import com.adinalaptuca.revolutconverter.ui.base.BaseMvp

/**
 * Created by adinalaptuca on Aug, 2019
 */

interface SplashMvp {
    interface View : BaseMvp.View{
        fun goToDashboard()
        fun killActivity()
    }

    interface Presenter : BaseMvp.Presenter{
        fun create()
    }
}