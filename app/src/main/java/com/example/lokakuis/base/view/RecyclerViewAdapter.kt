package com.example.lokakuis.base.view

import android.view.View
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil

abstract class RecyclerViewAdapter<Item : Any, VH : RecyclerViewHolder<Item>>(diffCallback: DiffUtil.ItemCallback<Item>)
    : PagingDataAdapter<Item, VH>(diffCallback) {

    private lateinit var itemClickListener: (item: Item) -> Unit

    fun addOnItemClickListener(listener: (item: Item) -> Unit) {
        itemClickListener = listener
    }

    private fun registerClickListener(view: View, position: Int) {
        if (::itemClickListener.isInitialized) {
            view.setOnClickListener {
                getItem(position)?.let {
                    itemClickListener.invoke(it)
                }
            }
        }
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
            registerClickListener(holder.itemView, position)
        }
    }
}
