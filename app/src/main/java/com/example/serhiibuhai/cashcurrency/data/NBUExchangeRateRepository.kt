package com.example.serhiibuhai.cashcurrency.data

import com.example.serhiibuhai.cashcurrency.domain.NBExchangeRateRepository
import com.example.serhiibuhai.cashcurrency.domain.NBUApi
import java.util.*

class NBUExchangeRateRepository(private val NBUApi: NBUApi) : NBExchangeRateRepository {
    @Throws(UnsuccessfulException::class)
    override fun queryToday(): List<NBExchangeRate> {
        val response = DataUtils.executeOrThrowGenericError {
            NBUApi.getTodayNBCurrency().execute()
        }
        return response.body()?.filter {
            it.currency != null
        }?.distinctBy { it.currency } ?: emptyList()
    }

    @Throws(UnsuccessfulException::class)
    override fun queryArchive(date: Date): List<NBExchangeRate> {
        val formattedDate = DateFormatters.nbuDateFormatter.format(date)
        val response = DataUtils.executeOrThrowGenericError {
            NBUApi.getArchiveNBCurrency(formattedDate).execute()
        }
        return response.body()?.filter {
            it.currency != null
        }?.distinctBy { it.currency } ?: emptyList()
    }
}