package com.example.serhiibuhai.cashcurrency.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.serhiibuhai.cashcurrency.data.Currency
import com.example.serhiibuhai.cashcurrency.data.UnsuccessfulException
import com.example.serhiibuhai.cashcurrency.domain.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class CurrencyViewModel(
    private val pbRatesUseCase: GetPBRatesUseCase,
    private val todayPBRatesUseCase: GetTodayPBRatesUseCase,
    private val nbuRatesUseCase: GetNBURatesUseCase,
    private val todayNBURatesUseCase: GetTodayNBURatesUseCase
) : ViewModel() {

    private val uiScope = CoroutineScope(Dispatchers.Main)

    val isFailed: MutableLiveData<Event<Boolean>> by lazy {
        MutableLiveData<Event<Boolean>>()
    }

    val pbExchangeRates: MutableLiveData<List<ExchangeRatePB>> by lazy {
        MutableLiveData<List<ExchangeRatePB>>()
    }

    val nbuExchangeRates: MutableLiveData<List<ExchangeRateNB>> by lazy {
        MutableLiveData<List<ExchangeRateNB>>()
    }

    val highlightedItemCurrency: MutableLiveData<Event<Currency?>> by lazy {
        MutableLiveData<Event<Currency?>>()
    }

    init {
        getTodayPBCurrency()
        getTodayNBUCurrency()
    }

    fun getTodayPBCurrency() {
        uiScope.launch {
            resetHighlightIfNeeded()
            try {
                val todayPBRates = withContext(Dispatchers.IO) {
                    todayPBRatesUseCase.execute()
                }
                pbExchangeRates.value = todayPBRates
            } catch (exception: UnsuccessfulException) {
                isFailed.value = Event(true)
            }
        }
    }

    fun getTodayNBUCurrency() {
        uiScope.launch {
            resetHighlightIfNeeded()
            try {
                val todayNBURates = withContext(Dispatchers.IO) {
                    todayNBURatesUseCase.execute()
                }
                nbuExchangeRates.value = todayNBURates
            } catch (exception: UnsuccessfulException) {
                isFailed.value = Event(true)
            }
        }
    }

    fun getArchivePBCurrency(date: Date) {
        uiScope.launch {
            resetHighlightIfNeeded()
            try {
                val result = withContext(Dispatchers.IO) {
                    pbRatesUseCase.execute(date)
                }
                pbExchangeRates.value = result
            } catch (exception: UnsuccessfulException) {
                isFailed.value = Event(true)
            }
        }
    }

    fun getArchiveNBUCurrency(date: Date) {
        uiScope.launch {
            resetHighlightIfNeeded()
            try {
                val result = withContext(Dispatchers.IO) {
                    nbuRatesUseCase.execute(date)
                }
                nbuExchangeRates.value = result
            } catch (exception: UnsuccessfulException) {
                isFailed.value = Event(true)
            }
        }
    }

    @Synchronized
    private fun resetHighlightIfNeeded() {
        highlightedItemCurrency.value?.peekContent()?.let {
            highlightedItemCurrency.value = Event(null)
        }
    }
}

class PBCurrencyViewModelFactory(
    private val pbRatesUseCase: GetPBRatesUseCase,
    private val todayPBRatesUseCase: GetTodayPBRatesUseCase,
    private val nbuRatesUseCase: GetNBURatesUseCase,
    private val todayNBURatesUseCase: GetTodayNBURatesUseCase
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CurrencyViewModel(pbRatesUseCase, todayPBRatesUseCase, nbuRatesUseCase, todayNBURatesUseCase) as T

    }
}