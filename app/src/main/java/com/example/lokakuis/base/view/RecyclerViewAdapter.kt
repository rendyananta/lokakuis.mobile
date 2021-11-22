package com.example.lokakuis.base.view

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class RecyclerViewAdapter<Item, VH : RecyclerViewHolder<Item>> : RecyclerView.Adapter<VH>() {
    private val items: MutableList<Item> = mutableListOf()
    private lateinit var itemClickListener: (item: Item) -> Unit

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(newItems: List<Item>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    fun addOnItemClickListener(listener: (item: Item) -> Unit) {
        itemClickListener = listener
    }

    private fun registerClickListener(view: View, position: Int) {
        if (::itemClickListener.isInitialized) {
            view.setOnClickListener {
                items[position].let {
                    itemClickListener.invoke(it)
                }
            }
        }
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(items[position])
        registerClickListener(holder.itemView, position)
    }

    override fun getItemCount(): Int = items.size
}
