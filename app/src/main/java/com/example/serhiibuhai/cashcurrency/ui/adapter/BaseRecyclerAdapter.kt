package com.example.serhiibuhai.cashcurrency.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import java.util.*

abstract class BaseRecyclerAdapter<Model>(protected var data:MutableList<Model> = mutableListOf()):
    RecyclerView.Adapter<BaseViewHolder<Model>>(){

    override fun onBindViewHolder(holder: BaseViewHolder<Model>, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    fun setList(mList: List<Model>){
        data.clear()
        data.addAll(0,mList)
        notifyDataSetChanged()
    }

    fun addItem(model: Model){
        data.add(model)
        notifyItemInserted(data.size - 1)
    }

    fun addItems(data: List<Model>) {
        val startIndex = this.data.size
        Collections.copy(this.data, data)
        notifyItemRangeInserted(startIndex, data.size)
    }

    fun updateItem(model: Model, position: Int){
        if (data.size >= position){
            data[position] = model
            notifyItemChanged(position)
        }
    }

    fun getItem(position: Int): Model? = if (data.size > position) data[position] else null

    fun clear(mList: MutableList<Model>){
        mList.clear()
        notifyItemRangeRemoved(0, mList.size)
    }

}