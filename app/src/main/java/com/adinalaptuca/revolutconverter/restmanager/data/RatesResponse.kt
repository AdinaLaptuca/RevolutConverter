package com.adinalaptuca.revolutconverter.restmanager.data

import com.google.gson.annotations.SerializedName
import retrofit2.Response

/**
 * Created by adinalaptuca on Aug, 2019
 */
data class RatesResponse(
    @SerializedName("base")
    var base: String,
    @SerializedName("date")
    var date: String,
    @SerializedName("rates")
    var rates: HashMap<String, Double>
)

