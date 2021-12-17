package com.example.lokakuis.ui.quiz

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.lokakuis.R
import com.example.lokakuis.base.view.RecyclerViewAdapter
import com.example.lokakuis.base.view.RecyclerViewHolder
import com.example.lokakuis.databinding.ItemQuizBinding
import com.example.lokakuis.entity.response.quiz.Quiz


class QuizAdapter : RecyclerViewAdapter<Quiz, QuizAdapter.ViewHolder>(SectionComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemQuizBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    inner class ViewHolder(private val binding: ItemQuizBinding) : RecyclerViewHolder<Quiz>(binding.root) {
        override fun bind(item: Quiz) {
            binding.quiz = item

            if (item.isMath) {
                binding.label.setBackgroundResource(R.color.colorAccentLight)
                binding.label.text = "Matematika"
            } else {
                binding.label.setBackgroundResource(R.color.colorBackgroundLight)
                binding.label.text = "Teori"
            }
        }
    }

    object SectionComparator : DiffUtil.ItemCallback<Quiz>() {
        override fun areItemsTheSame(oldItem: Quiz, newItem: Quiz): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Quiz, newItem: Quiz): Boolean {
            return oldItem.updatedAt == newItem.updatedAt
        }

    }
}