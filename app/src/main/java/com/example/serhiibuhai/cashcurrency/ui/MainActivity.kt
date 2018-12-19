package com.example.serhiibuhai.cashcurrency.ui

import android.os.Bundle
import android.os.PersistableBundle
import android.text.format.DateUtils
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.serhiibuhai.cashcurrency.R
import com.example.serhiibuhai.cashcurrency.data.Currency
import com.example.serhiibuhai.cashcurrency.domain.ExchangeRateNB
import com.example.serhiibuhai.cashcurrency.domain.ExchangeRatePB
import com.example.serhiibuhai.cashcurrency.domain.UseCaseProviders
import com.example.serhiibuhai.cashcurrency.ui.adapter.NBCurrencyAdapter
import com.example.serhiibuhai.cashcurrency.ui.adapter.OnItemPositionClickListener
import com.example.serhiibuhai.cashcurrency.ui.adapter.PBCurrencyListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : BaseActivity(), OnItemPositionClickListener, OnDatePickedListener {

    companion object {
        private const val PB_DATE_PICKER_ID = 0
        private const val NBU_DATE_PICKER_ID = 1
        private const val DIALOG_TAG_PREFIX = "datepicker-"
    }

    private lateinit var viewModel: CurrencyViewModel
    private val pbCurrencyListAdapter = PBCurrencyListAdapter(this)
    private val nbuCurrencyListAdapter = NBCurrencyAdapter()

    private val pbExchangeRatesObserver: Observer<List<ExchangeRatePB>> = Observer {
        pbCurrencyListAdapter.setList(it)
    }

    private val nbuExchangeRatesObserver: Observer<List<ExchangeRateNB>> = Observer {
        nbuCurrencyListAdapter.setList(it)
    }

    private val highlightedItemCurrencyObserver: Observer<Event<Currency?>> = Observer {
        it.peekContent().let { currency ->
            nbuCurrencyListAdapter.highlightItem(currency)
        }
    }

    private val isFailedObserver: Observer<Event<Boolean>> = Observer {
        it.getContentIfNotHandled()?.let {
            Toast.makeText(this, "Network error!", Toast.LENGTH_SHORT).show()
        }
    }


    override fun getContentView(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pbCurrencyRecyclerView.adapter = pbCurrencyListAdapter
        nbuCurrencyRecyclerView.adapter = nbuCurrencyListAdapter
        val dateformat = SimpleDateFormat("dd.MM.yyyy")
        val datetime = dateformat.format(Calendar.getInstance().time)
        pbDateTextView.text = datetime
        nbuDateTextView.text = datetime
        pbDateTextView.setOnClickListener {
            pickDate(PB_DATE_PICKER_ID)
        }
        nbuDateTextView.setOnClickListener {
            pickDate(NBU_DATE_PICKER_ID)
        }
        viewModel = ViewModelProviders.of(
            this, PBCurrencyViewModelFactory(
                UseCaseProviders.provideGetPBRatesUseCase(),
                UseCaseProviders.provideGetTodayPBRatesUseCase(),
                UseCaseProviders.provideGetNBURatesUseCase(),
                UseCaseProviders.provideGetTodayNBURatesUseCase()
            )
        ).get(CurrencyViewModel::class.java)
        viewModel.isFailed.observe(this, isFailedObserver)
        viewModel.highlightedItemCurrency.observe(this, highlightedItemCurrencyObserver)
        viewModel.pbExchangeRates.observe(this, pbExchangeRatesObserver)
        viewModel.nbuExchangeRates.observe(this, nbuExchangeRatesObserver)
    }

    override fun onItemClicked(position: Int) {
        pbCurrencyListAdapter.getItem(position)?.currency?.let {
            val nbuPosition = nbuCurrencyListAdapter.getItemPositionByCurrency(it)
            if (nbuPosition != -1) {
                nbuCurrencyRecyclerView.scrollToPosition(nbuPosition)
                viewModel.highlightedItemCurrency.value = Event(it)
            }
        }
    }

    private fun pickDate(dialogId: Int) {
        supportFragmentManager.findFragmentByTag("$DIALOG_TAG_PREFIX$dialogId").let {
            if (it == null || it.isVisible.not()) {
                val newFragment = DatePickerFragment.newDatePickerFragment(dialogId)
                newFragment.show(supportFragmentManager, "$DIALOG_TAG_PREFIX$dialogId")
            }
        }
    }

    override fun processPBDatePickerResult(year: Int, month: Int, day: Int, dialogId: Int) {
        val month_string = Integer.toString(month + 1)
        val day_string = Integer.toString(day)
        val year_string = Integer.toString(year)
        val dateMessage = "$day_string.$month_string.$year_string"
        val calendar = GregorianCalendar(year, month, day)
        val date = calendar.time
        when (dialogId) {
            PB_DATE_PICKER_ID -> {
                pbDateTextView.text = dateMessage
                if (DateUtils.isToday(date.time)) {
                    viewModel.getTodayPBCurrency()
                } else {
                    viewModel.getArchivePBCurrency(date)
                }
            }
            NBU_DATE_PICKER_ID -> {
                nbuDateTextView.text = dateMessage
                if (DateUtils.isToday(date.time)) {
                    viewModel.getTodayNBUCurrency()
                } else {
                    viewModel.getArchiveNBUCurrency(date)
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        val pbDate = pbDateTextView.text.toString()
        val nbuDate = nbuDateTextView.text.toString()
        outState.putString("pbDate", pbDate)
        outState.putString("nbuDate", nbuDate)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val restoredPBDate = savedInstanceState.getString("pbDate")
        pbDateTextView.text = restoredPBDate
        val restoredNBUDate = savedInstanceState.getString("nbuDate")
        nbuDateTextView.text = restoredNBUDate
    }
}
