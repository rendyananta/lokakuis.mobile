package com.example.lokakuis.base.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class RecyclerViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: T)
}
