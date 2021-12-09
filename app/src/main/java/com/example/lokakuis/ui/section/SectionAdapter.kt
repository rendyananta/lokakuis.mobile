package com.example.lokakuis.ui.section

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.lokakuis.base.view.RecyclerViewAdapter
import com.example.lokakuis.base.view.RecyclerViewHolder
import com.example.lokakuis.databinding.ItemSectionBinding
import com.example.lokakuis.entity.response.section.Section

class SectionAdapter : RecyclerViewAdapter<Section, SectionAdapter.ViewHolder>(SectionComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemSectionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    inner class ViewHolder(private val binding: ItemSectionBinding) : RecyclerViewHolder<Section>(binding.root) {
        override fun bind(item: Section) {
            binding.section = item
        }
    }

    object SectionComparator : DiffUtil.ItemCallback<Section>() {
        override fun areItemsTheSame(oldItem: Section, newItem: Section): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Section, newItem: Section): Boolean {
            return oldItem.updatedAt == newItem.updatedAt
        }

    }
}