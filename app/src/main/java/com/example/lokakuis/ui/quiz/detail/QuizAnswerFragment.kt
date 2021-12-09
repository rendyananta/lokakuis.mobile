package com.example.lokakuis.ui.quiz.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.lokakuis.R
import com.example.lokakuis.databinding.FragmentQuizAnswerBinding
import com.example.lokakuis.entity.response.quiz.Quiz

class QuizAnswerFragment(private val quiz: Quiz) : Fragment() {
    lateinit var binding: FragmentQuizAnswerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_quiz_answer, container, false)
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.quiz = quiz
    }
}