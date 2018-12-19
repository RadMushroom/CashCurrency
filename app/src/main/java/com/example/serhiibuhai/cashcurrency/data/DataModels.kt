package com.example.serhiibuhai.cashcurrency.data

import com.google.gson.annotations.SerializedName

data class PBCurrencyResponse(
    val date: String,
    val bank: String,
    val baseCurrency: Int,
    val baseCurrencyLit: String,
    val exchangeRate: List<ApiP24ExchangeRate>
)

data class ApiP24ExchangeRate(
    val baseCurrency: Currency,
    val currency: Currency,
    val sellRateNB: Double,
    val purchaseRateNB: Double,
    val saleRate: Double?,
    val purchaseRate: Double?
)



data class TodayP24ApiExchangeRate(
    @SerializedName("ccy")
    val currency: Currency,
    @SerializedName("base_ccy")
    val baseCurrency: Currency,
    @SerializedName("buy")
    val purchaseRate: Double,
    @SerializedName("sale")
    val saleRate: Double

)

data class NBExchangeRate(
    @SerializedName("r030")
    val currencyCode: Int,
    @SerializedName("txt")
    val currencyName: String,
    val rate: Double,
    @SerializedName("cc")
    val currency: Currency,
    val exchangeDate: String)

enum class Currency {

    @SerializedName("UAH")
    UAH,
    @SerializedName("USD")
    USD,
    @SerializedName("EUR")
    EUR,
    @SerializedName("RUB")
    RUB,
    @SerializedName("RUR")
    RUR,
    @SerializedName("CHF")
    CHF,
    @SerializedName("GBP")
    GBP,
    @SerializedName("PLZ")
    PLZ,
    @SerializedName("SEK")
    SEK,
    @SerializedName("XAU")
    XAU,
    @SerializedName("CAD")
    CAD,
    @SerializedName("JPY")
    JPY,
    @SerializedName("AVD")
    AVD,
    @SerializedName("CNY")
    CNY,
    @SerializedName("CZK")
    CZK,
    @SerializedName("HRK")
    HRK,
    @SerializedName("DKK")
    DKK,
    @SerializedName("HKD")
    HKD,
    @SerializedName("HUF")
    HUF,
    @SerializedName("INR")
    INR,
    @SerializedName("IDR")
    IDR,
    @SerializedName("IRR")
    IRR,
    @SerializedName("ILS")
    ILS,
    @SerializedName("KZT")
    KZT,
    @SerializedName("KRW")
    KRW,
    @SerializedName("MXN")
    MXN,
    @SerializedName("MPL")
    MPL,
    @SerializedName("NZD")
    NZD,
    @SerializedName("RLN")
    RLN,
    @SerializedName("DZP")
    DZP,
    @SerializedName("BDT")
    BDT,
    @SerializedName("AMD")
    AMD,
    @SerializedName("NOK")
    NOK,
    @SerializedName("SAR")
    SAR,
    @SerializedName("SGD")
    SGD,
    @SerializedName("ZAR")
    ZAR,
    @SerializedName("EGP")
    EGP,
    @SerializedName("BYN")
    BYN,
    @SerializedName("AZN")
    AZN,
    @SerializedName("ROW")
    ROW,
    @SerializedName("TRY")
    TRY,
    @SerializedName("XDK")
    XDK,
}