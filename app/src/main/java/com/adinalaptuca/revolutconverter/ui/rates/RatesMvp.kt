package com.adinalaptuca.revolutconverter.ui.rates

import com.adinalaptuca.revolutconverter.restmanager.data.RatesResponse
import com.adinalaptuca.revolutconverter.ui.base.BaseMvp

/**
 * Created by adinalaptuca on Aug, 2019
 */

interface RatesMvp {
    interface View : BaseMvp.View {
        fun setRatesList(response: RatesResponse)
    }

    interface Presenter : BaseMvp.Presenter {
        fun getRates(base: String)
    }
}