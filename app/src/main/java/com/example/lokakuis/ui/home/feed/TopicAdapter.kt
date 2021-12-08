package com.example.lokakuis.ui.home.feed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.lokakuis.base.view.RecyclerViewAdapter
import com.example.lokakuis.base.view.RecyclerViewHolder
import com.example.lokakuis.databinding.ItemTopicBinding
import com.example.lokakuis.entity.response.topic.Topic

class TopicAdapter : RecyclerViewAdapter<Topic, TopicAdapter.TopicViewHolder>(TopicComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicViewHolder {
        return TopicViewHolder(
            ItemTopicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    inner class TopicViewHolder(private val binding: ItemTopicBinding) : RecyclerViewHolder<Topic>(binding.root) {
        override fun bind(item: Topic) {
            binding.topic = item
        }
    }

    object TopicComparator : DiffUtil.ItemCallback<Topic>() {
        override fun areItemsTheSame(oldItem: Topic, newItem: Topic): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Topic, newItem: Topic): Boolean {
            return oldItem.updatedAt == newItem.updatedAt
        }

    }
}