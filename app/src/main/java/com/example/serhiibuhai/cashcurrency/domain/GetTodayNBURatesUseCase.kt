package com.example.serhiibuhai.cashcurrency.domain

import com.example.serhiibuhai.cashcurrency.data.NBExchangeRate

class GetTodayNBURatesUseCase(private val exchangeRateRepository: NBExchangeRateRepository) {

    private val exchangeRateMapper: (NBExchangeRate) -> ExchangeRateNB = {
        ExchangeRateNB(
            it.currencyName,
            it.rate,
            it.currency
        )
    }

    fun execute(): List<ExchangeRateNB> {
        val result = exchangeRateRepository.queryToday()
        return result.map(exchangeRateMapper)
    }
}