package com.example.dva232.view.util

import android.location.Geocoder
import android.location.Location
import androidx.compose.runtime.MutableState
import com.google.android.gms.location.FusedLocationProviderClient
import java.util.Currency
import java.util.Locale

class Functions {
    companion object {
        fun getCurrencyForLocation(
            fusedLocationClient: FusedLocationProviderClient,
            geocoder: Geocoder,
            currencyCode: MutableState<String>,
            currencies: MutableList<com.example.dva232.view.util.Currency>
        ) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                location?.let {
                    val addressList = geocoder.getFromLocation(it.latitude, it.longitude, 1)
                    if (!addressList.isNullOrEmpty()) {
                        val countryCode = addressList[0].countryCode
                        if (!countryCode.isNullOrEmpty()) {
                            val currency = Currency.getInstance(Locale("", countryCode))
                            currencyCode.value = currency.currencyCode
                            this.recalculateRates(currencies, currencyCode)
                        } else {
                            currencyCode.value = "Unknown"
                        }
                    }
                }
            }
        }

         fun recalculateRates(currencies: MutableList<com.example.dva232.view.util.Currency>, newCurrencyState: MutableState<String>) {
            val newCurrency = currencies.find { currency -> currency.code == newCurrencyState.value }
            currencies.forEach { currency ->
                run {
                    currency.rate = String.format("%.3f", currency.rate / newCurrency!!.rate).toDouble()
                }
            }
        }

    }


}