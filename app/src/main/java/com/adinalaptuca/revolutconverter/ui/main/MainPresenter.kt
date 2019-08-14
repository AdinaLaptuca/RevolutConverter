package com.adinalaptuca.revolutconverter.ui.main

import com.adinalaptuca.revolutconverter.data.networkmanager.NetworkManager
import com.adinalaptuca.revolutconverter.ui.base.BasePresenter

/**
 * Created by adinalaptuca on Aug, 2019
 */

class MainPresenter(private val networkManager: NetworkManager) : BasePresenter<MainMvp.View>(networkManager), MainMvp.Presenter {
    override fun create() {

    }
}
