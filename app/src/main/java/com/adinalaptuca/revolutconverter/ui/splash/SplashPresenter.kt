package com.adinalaptuca.revolutconverter.ui.splash

import android.os.Handler
import com.adinalaptuca.revolutconverter.data.networkmanager.NetworkManager
import com.adinalaptuca.revolutconverter.ui.base.BasePresenter

/**
 * Created by adinalaptuca on Aug, 2019
 */

class SplashPresenter(private val networkManager: NetworkManager) : BasePresenter<SplashMvp.View>(networkManager), SplashMvp.Presenter {

    private val SPLASH_TIME_OUT: Long = 3000 // 3 sec

    override fun create() {
//        Handler().postDelayed({
//            view?.goToDashboard()
//            view?.killActivity()
//        }, SPLASH_TIME_OUT)
    }
}