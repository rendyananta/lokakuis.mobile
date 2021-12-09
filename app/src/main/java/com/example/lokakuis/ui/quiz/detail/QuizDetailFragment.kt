package com.example.lokakuis.ui.quiz.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.lokakuis.R
import com.example.lokakuis.base.lifecycleowner.AuthenticatedFragment
import com.example.lokakuis.databinding.FragmentQuizDetailBinding
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf

class QuizDetailFragment : AuthenticatedFragment<FragmentQuizDetailBinding, QuizDetailViewModel>() {

    private val args: QuizDetailFragmentArgs by navArgs()

    private val quizAdapter by lazy {
        QuizAdapter(this)
    }

    override fun setupBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) {
        super.setupBinding(inflater, container, savedInstanceState)
        binding.viewModel = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.pager.apply {
            adapter = quizAdapter
        }

        TabLayoutMediator(binding.tab, binding.pager) { tab, position ->
            tab.text = when(position) {
                0 -> "Pertanyaan"
                else -> "Jawaban"
            }
        }.attach()
    }

    inner class QuizAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
        override fun getItemCount(): Int = 2

        override fun createFragment(position: Int): Fragment {
            return when(position) {
                0 -> QuizQuestionFragment(args.quiz)
                else -> QuizAnswerFragment(args.quiz)
            }
        }

    }

    override val layoutId: Int
        get() = R.layout.fragment_quiz_detail
    override val viewModel: QuizDetailViewModel
        get() = getViewModel { parametersOf(args.topic, args.section, args.quiz) }
}