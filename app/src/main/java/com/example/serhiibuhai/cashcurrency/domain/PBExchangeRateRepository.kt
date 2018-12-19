package com.example.serhiibuhai.cashcurrency.domain

import com.example.serhiibuhai.cashcurrency.data.ApiP24ExchangeRate
import com.example.serhiibuhai.cashcurrency.data.TodayP24ApiExchangeRate
import com.example.serhiibuhai.cashcurrency.data.UnsuccessfulException
import java.util.*

interface PBExchangeRateRepository {
    @Throws(UnsuccessfulException::class)
    fun query(): List<TodayP24ApiExchangeRate>
    @Throws(UnsuccessfulException::class)
    fun query(date: Date): List<ApiP24ExchangeRate>
}