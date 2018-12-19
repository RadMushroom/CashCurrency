package com.example.serhiibuhai.cashcurrency.domain

import com.example.serhiibuhai.cashcurrency.data.APIProviders
import com.example.serhiibuhai.cashcurrency.data.NBUExchangeRateRepository
import com.example.serhiibuhai.cashcurrency.data.P24ExchangeRateRepository

object UseCaseProviders {

    fun provideGetPBRatesUseCase() = GetPBRatesUseCase(P24ExchangeRateRepository(APIProviders.provideP24API()))

    fun provideGetTodayPBRatesUseCase() = GetTodayPBRatesUseCase(P24ExchangeRateRepository(APIProviders.provideP24API()))

    fun provideGetNBURatesUseCase() = GetNBURatesUseCase(NBUExchangeRateRepository(APIProviders.provideNBAPI()))

    fun provideGetTodayNBURatesUseCase() = GetTodayNBURatesUseCase(NBUExchangeRateRepository(APIProviders.provideNBAPI()))
}