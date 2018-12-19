package com.example.serhiibuhai.cashcurrency.domain

import com.example.serhiibuhai.cashcurrency.data.Currency

data class ExchangeRatePB(
    val currency: Currency,
    val sellRate: Double,
    val purchaseRate: Double
)

data class ExchangeRateNB(
    val currencyName: String,
    val rate: Double,
    val currency: Currency
)