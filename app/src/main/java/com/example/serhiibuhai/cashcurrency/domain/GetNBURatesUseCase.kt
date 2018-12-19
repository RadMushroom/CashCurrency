package com.example.serhiibuhai.cashcurrency.domain

import com.example.serhiibuhai.cashcurrency.data.NBExchangeRate
import java.util.*

class GetNBURatesUseCase(private val exchangeRateRepository: NBExchangeRateRepository) {

    private val exchangeRateMapper: (NBExchangeRate) -> ExchangeRateNB = {
        ExchangeRateNB(
            it.currencyName,
            it.rate,
            it.currency
        )
    }

    fun execute(date: Date): List<ExchangeRateNB> {
        val result = exchangeRateRepository.queryArchive(date)
        return result.map(exchangeRateMapper)
    }


}