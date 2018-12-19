package com.example.serhiibuhai.cashcurrency.domain

import com.example.serhiibuhai.cashcurrency.data.NBExchangeRate
import com.example.serhiibuhai.cashcurrency.data.UnsuccessfulException
import java.util.*

interface NBExchangeRateRepository {
    @Throws(UnsuccessfulException::class)
    fun queryToday(): List<NBExchangeRate>
    @Throws(UnsuccessfulException::class)
    fun queryArchive(date: Date): List<NBExchangeRate>
}