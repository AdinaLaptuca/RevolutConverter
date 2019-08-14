package com.adinalaptuca.revolutconverter.ui.rates

import com.adinalaptuca.revolutconverter.dagger.PerFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by adinalaptuca on Aug, 2019
 */

@PerFragment
@Module
internal abstract class RatesProvider {

    @PerFragment
    @ContributesAndroidInjector(modules = [RatesModule::class])
    internal abstract fun provideRatesFragment(): RatesFragment
}