package com.example.serhiibuhai.cashcurrency.data

import com.example.serhiibuhai.cashcurrency.domain.NBUApi
import com.example.serhiibuhai.cashcurrency.domain.PBApi
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIProviders {

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    private val PB_API: PBApi = Retrofit.Builder()
        .baseUrl(PBApi.BASE_URL_PB)
        .addConverterFactory(GsonConverterFactory.create(Gson()))
        .client(okHttpClient)
        .build()
        .create(PBApi::class.java)

    private val NBU_API: NBUApi = Retrofit.Builder()
        .baseUrl(NBUApi.BASE_URL_NB)
        .addConverterFactory(GsonConverterFactory.create(Gson()))
        .client(okHttpClient)
        .build()
        .create(NBUApi::class.java)

    fun provideP24API(): PBApi = PB_API

    fun provideNBAPI(): NBUApi = NBU_API

}