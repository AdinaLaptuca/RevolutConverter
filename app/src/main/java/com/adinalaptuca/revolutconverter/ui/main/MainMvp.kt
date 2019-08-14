package com.adinalaptuca.revolutconverter.ui.main

import com.adinalaptuca.revolutconverter.ui.base.BaseMvp

/**
 * Created by adinalaptuca on Aug, 2019
 */

interface MainMvp {
    interface View : BaseMvp.View {
    }

    interface Presenter : BaseMvp.Presenter {
        fun create()
    }
}