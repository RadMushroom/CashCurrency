package com.example.serhiibuhai.cashcurrency.domain

import com.example.serhiibuhai.cashcurrency.data.Currency
import java.util.*

class GetPBRatesUseCase(private val PBExchangeRateRepository: PBExchangeRateRepository) {
    fun execute(date: Date): List<ExchangeRatePB> {
        val result = PBExchangeRateRepository.query(date)
        return result.filter {
            it.saleRate != null
                    && it.purchaseRate != null
                    && (it.currency == Currency.EUR
                    || it.currency == Currency.USD
                    || it.currency == Currency.RUB)
        }.map {
            ExchangeRatePB(
                it.currency,
                it.saleRate!!,
                it.purchaseRate!!
            ) // Use force unwrap according to filtering operation above
        }
    }


}