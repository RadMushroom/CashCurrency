package com.example.serhiibuhai.cashcurrency.ui.adapter

import android.view.View
import androidx.core.content.ContextCompat
import com.example.serhiibuhai.cashcurrency.R
import com.example.serhiibuhai.cashcurrency.domain.ExchangeRateNB
import kotlinx.android.synthetic.main.nbu_currency_item.view.*

class NBCurrencyViewHolder(view: View) : BaseViewHolder<ExchangeRateNB>(view) {

    override fun bind(model: ExchangeRateNB) {
        with(itemView) {
            currencyTextView.text = model.currencyName
            sellRate.text = model.rate.toString()
            unitTextView.text = "1 ${model.currency}"
        }
    }

    fun highlight(enabled: Boolean){
        if (enabled){
            itemView.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.colorRed400))
        } else {
            setColorByPosition()
        }
    }

    private fun setColorByPosition(){
        if (adapterPosition % 2 == 0){
            itemView.setBackgroundColor(ContextCompat.getColor(itemView.context, android.R.color.white))
        } else {
            itemView.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.colorGreen400))
        }
    }
}