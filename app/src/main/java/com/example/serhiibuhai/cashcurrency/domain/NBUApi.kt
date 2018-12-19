package com.example.serhiibuhai.cashcurrency.domain

import com.example.serhiibuhai.cashcurrency.data.NBExchangeRate
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NBUApi {

    companion object {
        const val BASE_URL_NB = "https://bank.gov.ua/NBUStatService/v1/statdirectory/"
    }

    @GET("exchange")
    fun getTodayNBCurrency(@Query("json") json: Boolean = true): Call<List<NBExchangeRate>>

    @GET("exchange")
    fun getArchiveNBCurrency(
        @Query("date") date: String,
        @Query("json") json: Boolean = true
    ): Call<List<NBExchangeRate>>
}