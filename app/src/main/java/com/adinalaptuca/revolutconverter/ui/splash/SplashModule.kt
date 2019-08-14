package com.adinalaptuca.revolutconverter.ui.splash

import com.adinalaptuca.revolutconverter.dagger.PerActivity
import com.adinalaptuca.revolutconverter.data.networkmanager.NetworkManager
import dagger.Module
import dagger.Provides

/**
 * Created by adinalaptuca on Aug, 2019
 */

@PerActivity
@Module
class SplashModule {

    @Provides
    @PerActivity
    internal fun provideSplashPresenter(networkManager: NetworkManager): SplashMvp.Presenter = SplashPresenter(networkManager)
}