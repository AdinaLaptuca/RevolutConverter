package com.adinalaptuca.revolutconverter.restmanager

import com.adinalaptuca.revolutconverter.restmanager.data.RatesResponse
import com.google.gson.Gson
import io.reactivex.Flowable
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

/**
 * Created by adinalaptuca on Aug, 2019
 */
interface RatesService {

    @Headers(value = ["Content-Type: application/json"])
    @GET("latest")
    fun getRates(@Query("base") base: String): Flowable<RatesResponse>

    companion object {
        fun create(gson: Gson, okHttpClient: OkHttpClient): RatesService {
            return Retrofit.Builder()
                .baseUrl("https://revolut.duckdns.org/")
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(RatesService::class.java)
        }
    }
}
