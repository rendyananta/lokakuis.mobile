package com.example.lokakuis.ui.quiz.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.lokakuis.R
import com.example.lokakuis.databinding.FragmentQuizQuestionBinding
import com.example.lokakuis.entity.response.quiz.Quiz

class QuizQuestionFragment(private val quiz: Quiz) : Fragment() {

    lateinit var binding: FragmentQuizQuestionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_quiz_question, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.quiz = quiz

        if (quiz.isMath) {
            binding.label.setBackgroundResource(R.color.colorAccentLight)
            binding.label.text = "Matematika"
        } else {
            binding.label.setBackgroundResource(R.color.colorBackgroundLight)
            binding.label.text = "Teori"
        }
    }

}