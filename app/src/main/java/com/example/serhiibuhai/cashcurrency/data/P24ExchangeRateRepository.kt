package com.example.serhiibuhai.cashcurrency.data

import com.example.serhiibuhai.cashcurrency.domain.PBApi
import com.example.serhiibuhai.cashcurrency.domain.PBExchangeRateRepository
import java.util.*

class P24ExchangeRateRepository(private val PBApi: PBApi) : PBExchangeRateRepository {

    @Throws(UnsuccessfulException::class)
    override fun query(): List<TodayP24ApiExchangeRate> {
        val response = DataUtils.executeOrThrowGenericError {
            PBApi.getTodayPBCurrency().execute()
        }
        return response.body()?.filter {
            it.currency != null && it.baseCurrency != null
        } ?: emptyList()
    }

    @Throws(UnsuccessfulException::class)
    override fun query(date: Date): List<ApiP24ExchangeRate> {
        val formattedDate = DateFormatters.pbDateFormatter.format(date)
        val response = DataUtils.executeOrThrowGenericError {
            PBApi.getPBCurrency(formattedDate).execute()
        }
        return response.body()?.exchangeRate?.filter {
            it.currency != null && it.baseCurrency != null
        } ?: emptyList()
    }
}