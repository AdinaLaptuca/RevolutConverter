package com.adinalaptuca.revolutconverter.dagger.builder

import com.adinalaptuca.revolutconverter.dagger.PerActivity
import com.adinalaptuca.revolutconverter.ui.main.MainActivity
import com.adinalaptuca.revolutconverter.ui.main.MainModule
import com.adinalaptuca.revolutconverter.ui.rates.RatesProvider
import com.adinalaptuca.revolutconverter.ui.splash.SplashActivity
import com.adinalaptuca.revolutconverter.ui.splash.SplashModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by adinalaptuca on Aug, 2019
 */

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [(SplashModule::class)])
    @PerActivity
    abstract fun bindSplashActivity(): SplashActivity

    @ContributesAndroidInjector(modules = [(MainModule::class), (RatesProvider::class)])
    @PerActivity
    abstract fun bindMainActivity(): MainActivity
}