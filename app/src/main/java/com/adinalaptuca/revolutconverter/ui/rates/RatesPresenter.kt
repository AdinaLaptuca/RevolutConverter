package com.adinalaptuca.revolutconverter.ui.rates

import android.util.Log
import com.adinalaptuca.revolutconverter.data.RatesManager
import com.adinalaptuca.revolutconverter.data.networkmanager.NetworkManager
import com.adinalaptuca.revolutconverter.ui.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by adinalaptuca on Aug, 2019
 */

class RatesPresenter(private val ratesManager: RatesManager, networkManager: NetworkManager) :
    BasePresenter<RatesMvp.View>(networkManager), RatesMvp.Presenter {

    override fun getRates(base: String) {
        subscription.add(
            ratesManager.getRates(base)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    Log.e("AdinaTest", "raspuns: " + response)
                    view?.setRatesList(response)
                },
                    { error ->
                        Log.e("AdinaTest", "eroare" + "${error.message}" + " " + error)
                    })
        )
    }
}