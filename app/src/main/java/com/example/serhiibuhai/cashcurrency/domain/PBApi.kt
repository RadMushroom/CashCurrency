package com.example.serhiibuhai.cashcurrency.domain

import com.example.serhiibuhai.cashcurrency.data.PBCurrencyResponse
import com.example.serhiibuhai.cashcurrency.data.TodayP24ApiExchangeRate
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PBApi {
    companion object {
        const val BASE_URL_PB = " https://api.privatbank.ua/"
    }

    @GET("p24api/exchange_rates")
    fun getPBCurrency(
        @Query("date") date: String,
        @Query("json") json: Boolean = true
    ): Call<PBCurrencyResponse>

    @GET("p24api/pubinfo")
    fun getTodayPBCurrency(
        @Query("exchange") exchange: Boolean = true,
        @Query("json") json: Boolean = true,
        @Query("coursid") courseId: Int = 5
    ): Call<List<TodayP24ApiExchangeRate>>
}