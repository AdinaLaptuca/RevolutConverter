package com.adinalaptuca.revolutconverter.ui.rates

import android.util.Log
import com.adinalaptuca.revolutconverter.data.RatesManager
import com.adinalaptuca.revolutconverter.data.networkmanager.NetworkManager
import com.adinalaptuca.revolutconverter.ui.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * Created by adinalaptuca on Aug, 2019
 */

class RatesPresenter(private val ratesManager: RatesManager, networkManager: NetworkManager) :
    BasePresenter<RatesMvp.View>(networkManager), RatesMvp.Presenter {

    protected var ratesSubscription = CompositeDisposable()

    override fun getRates(base: String) {
        view?.showLoading(true)
        ratesSubscription.add(
            ratesManager.getRates(base)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .repeatWhen { it.delay(1, TimeUnit.SECONDS) }
                .subscribe({ response ->
                    Log.e("AdinaTest", "raspuns: " + response)
                    view?.setRatesList(response)
                    view?.showLoading(false)
                    view?.showErrorMessage(false)

                },
                    { error ->
                        view?.showLoading(false)
                        view?.showErrorMessage(true)
                        Log.e("AdinaTest", "eroare" + "${error.message}" + " " + error)
                    })
        )
    }

    override fun changeRate(base:String){
        ratesSubscription.clear()
        getRates(base)
    }
}