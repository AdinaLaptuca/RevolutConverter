package com.adinalaptuca.revolutconverter.data

import com.adinalaptuca.revolutconverter.restmanager.data.RatesResponse
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Created by adinalaptuca on Aug, 2019
 */

interface RatesManager {
    fun getRates(base: String): Flowable<RatesResponse>
}
