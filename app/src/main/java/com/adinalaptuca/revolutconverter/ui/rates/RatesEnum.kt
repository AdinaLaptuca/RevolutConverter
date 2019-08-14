package com.adinalaptuca.revolutconverter.ui.rates

import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import com.adinalaptuca.revolutconverter.R

/**
 * Created by adinalaptuca on Aug, 2019
 */

enum class RatesEnum(val value: String, @StringRes val displayValue: Int, @DrawableRes val drawableRes: Int) {
    EUR("EUR", R.string.EUR, R.drawable.ic_eur),
    AUD("AUD", R.string.AUD, R.drawable.ic_aud),
    BGN("BGN", R.string.BGN, R.drawable.ic_bgn),
    BRL("BRL", R.string.BRL, R.drawable.ic_brl),
    CAD("CAD", R.string.CAD, R.drawable.ic_cad),
    CHF("CHF", R.string.CHF, R.drawable.ic_chf),
    CNY("CNY", R.string.CNY, R.drawable.ic_cny),
    CZK("CZK", R.string.CZK, R.drawable.ic_czk),
    DKK("DKK", R.string.DKK, R.drawable.ic_dkk),
    GBP("GBP", R.string.GBP, R.drawable.ic_gbp),
    HKD("HKD", R.string.HKD, R.drawable.ic_hkd),
    HRK("HRK", R.string.HRK, R.drawable.ic_hrk),
    HUF("HUF", R.string.HUF, R.drawable.ic_huf),
    IDR("IDR", R.string.IDR, R.drawable.ic_idr),
    ILS("ILS", R.string.ILS, R.drawable.ic_ils),
    INR("INR", R.string.INR, R.drawable.ic_inr),
    ISK("ISK", R.string.ISK, R.drawable.ic_isk),
    JPY("JPY", R.string.JPY, R.drawable.ic_jpy),
    KRW("KRW", R.string.KRW, R.drawable.ic_krw),
    MXN("MXN", R.string.MXN, R.drawable.ic_mxn),
    MYR("MYR", R.string.MYR, R.drawable.ic_myr),
    NOK("NOK", R.string.NOK, R.drawable.ic_nok),
    NZD("NZD", R.string.NZD, R.drawable.ic_nzd),
    PHP("PHP", R.string.PHP, R.drawable.ic_php),
    PLN("PLN", R.string.PLN, R.drawable.ic_pln),
    RON("RON", R.string.RON, R.drawable.ic_ron),
    RUB("RUB", R.string.RUB, R.drawable.ic_rub),
    SEK("SEK", R.string.SEK, R.drawable.ic_sek),
    SGD("SGD", R.string.SGD, R.drawable.ic_sgd),
    THB("THB", R.string.THB, R.drawable.ic_thb),
    TRY("TRY", R.string.TRY, R.drawable.ic_try),
    USD("USD", R.string.USD, R.drawable.ic_usd),
    ZAR("ZAR", R.string.ZAR, R.drawable.ic_zar);

    companion object {

        fun getEnumFromValue(value: String): RatesEnum? {
            for (rate in RatesEnum.values()) {
                if (rate.value.toUpperCase() == value.toUpperCase())
                    return rate
            }
            return null
        }
    }
}

