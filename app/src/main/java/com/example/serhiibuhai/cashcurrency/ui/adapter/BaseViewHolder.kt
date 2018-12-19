package com.example.serhiibuhai.cashcurrency.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<in Model>(itemView : View) : RecyclerView.ViewHolder(itemView) {

    internal abstract fun bind(model: Model)

}