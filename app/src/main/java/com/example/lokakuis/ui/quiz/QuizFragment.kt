package com.example.lokakuis.ui.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.lokakuis.base.lifecycleowner.AuthenticatedFragment
import com.example.lokakuis.databinding.FragmentQuizBinding
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf

class QuizFragment : AuthenticatedFragment<FragmentQuizBinding, QuizViewModel>() {

    private val args: QuizFragmentArgs by navArgs()

    override fun setupBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) {
        super.setupBinding(inflater, container, savedInstanceState)
        binding.viewModel = viewModel
    }

    override fun observeViewModel() {
        super.observeViewModel()
    }

    override val navigateToLoginId: Int
        get() = TODO("Not yet implemented")
    override val layoutId: Int
        get() = TODO("Not yet implemented")
    override val viewModel: QuizViewModel
        get() = getViewModel { parametersOf(args.topic, args.section) }

}