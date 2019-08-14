package com.adinalaptuca.revolutconverter.data

import com.adinalaptuca.revolutconverter.restmanager.RatesService
import com.adinalaptuca.revolutconverter.restmanager.data.RatesResponse
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Created by adinalaptuca on Aug, 2019
 */
class RatesManagerImplementation(private val ratesService: RatesService) : RatesManager {

    override fun getRates(base: String): Flowable<RatesResponse> {
        return ratesService.getRates(base)
    }
}