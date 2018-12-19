package com.example.serhiibuhai.cashcurrency.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.serhiibuhai.cashcurrency.R
import com.example.serhiibuhai.cashcurrency.data.Currency
import com.example.serhiibuhai.cashcurrency.domain.ExchangeRateNB

class NBCurrencyAdapter : BaseRecyclerAdapter<ExchangeRateNB>() {

    private var highlightedItemPosition: Int = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ExchangeRateNB> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.nbu_currency_item, parent, false)
        return NBCurrencyViewHolder(view)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ExchangeRateNB>, position: Int) {
        super.onBindViewHolder(holder, position)
        if (holder is NBCurrencyViewHolder) {
            holder.highlight(position == highlightedItemPosition)
        }
    }

    fun getItemPositionByCurrency(currency: Currency): Int {
        return data.firstOrNull { it.currency == currency }?.let { data.indexOf(it) } ?: -1
    }

    fun highlightItem(currency: Currency?) {
        val previousPosition = highlightedItemPosition
        currency?.let {
            val position = getItemPositionByCurrency(it)
            if (position != -1) {
                highlightedItemPosition = position
                notifyItemChanged(position)
                if (previousPosition != -1) {
                    notifyItemChanged(previousPosition)
                }
            }
        } ?: run {
            highlightedItemPosition = -1
            if (previousPosition != -1) {
                notifyItemChanged(previousPosition)
            }
        }
    }
}