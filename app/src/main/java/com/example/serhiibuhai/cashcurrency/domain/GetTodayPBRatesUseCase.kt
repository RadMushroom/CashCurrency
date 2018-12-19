package com.example.serhiibuhai.cashcurrency.domain

import com.example.serhiibuhai.cashcurrency.data.Currency

class GetTodayPBRatesUseCase(private val PBExchangeRateRepository: PBExchangeRateRepository) {

    fun execute(): List<ExchangeRatePB> {
        val result = PBExchangeRateRepository.query()
        return result.map {
            ExchangeRatePB(
                if (it.currency == Currency.RUR) Currency.RUB else it.currency,
                it.saleRate,
                it.purchaseRate
            )
        }
    }

}