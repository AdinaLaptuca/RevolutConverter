package com.adinalaptuca.revolutconverter.ui.rates

import com.adinalaptuca.revolutconverter.dagger.PerFragment
import com.adinalaptuca.revolutconverter.data.RatesManager
import com.adinalaptuca.revolutconverter.data.networkmanager.NetworkManager
import dagger.Module
import dagger.Provides

/**
 * Created by adinalaptuca on Aug, 2019
 */

@PerFragment
@Module
class RatesModule {

    @Provides
    @PerFragment
    internal fun provideRatesAdapter(): RatesAdapter = RatesAdapter()

    @Provides
    @PerFragment
    internal fun provideRatesPresenter(ratesManager: RatesManager, networkManager: NetworkManager): RatesMvp.Presenter = RatesPresenter(ratesManager, networkManager)
}