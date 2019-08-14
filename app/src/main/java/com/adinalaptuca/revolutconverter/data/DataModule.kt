package com.adinalaptuca.revolutconverter.data

import android.content.Context
import com.adinalaptuca.revolutconverter.data.networkmanager.NetworkManager
import com.adinalaptuca.revolutconverter.data.networkmanager.NetworkManagerImplementation
import com.adinalaptuca.revolutconverter.restmanager.RatesService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Singleton

/**
 * Created by adinalaptuca on Aug, 2019
 */

@Module
internal open class DataModule {

    @Provides
    @Singleton
    open fun provideGson(): Gson = GsonBuilder()
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ss zzz")
        .create()

    @Provides
    @Singleton
    open fun provideOkHttpClient(context: Context): OkHttpClient = OkHttpClient()


    @Provides
    @Singleton
    open fun provideNetworkManager(context: Context): NetworkManager = NetworkManagerImplementation(context)

    @Provides
    @Singleton
    open fun provideRatesService(gson: Gson, okHttpClient: OkHttpClient): RatesService = RatesService.create(gson, okHttpClient)

    @Provides
    @Singleton
    open fun provideRatesManager(ratesService: RatesService): RatesManager
            = RatesManagerImplementation(ratesService)

}