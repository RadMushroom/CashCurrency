package com.example.serhiibuhai.cashcurrency.ui.adapter

import android.view.View
import com.example.serhiibuhai.cashcurrency.domain.ExchangeRatePB
import kotlinx.android.synthetic.main.pb_currency_item.view.*

class PBCurrencyListViewHolder (view: View, itemClickListener: OnItemPositionClickListener) : BaseViewHolder<ExchangeRatePB>(view) {

    init {
        itemView.setOnClickListener { itemClickListener.onItemClicked(adapterPosition) }
    }

    override fun bind(model: ExchangeRatePB) {
        with(itemView) {
            currencyPBTextView.text = model.currency.name
            purchaseRate.text = model.purchaseRate.toString()
            sellRate.text = model.sellRate.toString()
        }
    }
}