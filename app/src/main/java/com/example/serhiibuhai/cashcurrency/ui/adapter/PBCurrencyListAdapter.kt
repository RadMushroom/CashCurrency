package com.example.serhiibuhai.cashcurrency.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.serhiibuhai.cashcurrency.R
import com.example.serhiibuhai.cashcurrency.domain.ExchangeRatePB

class PBCurrencyListAdapter(private var itemClickListener: OnItemPositionClickListener) : BaseRecyclerAdapter<ExchangeRatePB>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ExchangeRatePB> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pb_currency_item, parent, false)
        return PBCurrencyListViewHolder(view, itemClickListener)
    }

}