package com.example.lokakuis.ui.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lokakuis.R
import com.example.lokakuis.base.extensions.setVisibleOrGone
import com.example.lokakuis.base.lifecycleowner.AuthenticatedFragment
import com.example.lokakuis.base.network.Errors
import com.example.lokakuis.databinding.FragmentQuizBinding
import com.example.lokakuis.ui.section.SectionFragmentDirections
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf

class QuizFragment : AuthenticatedFragment<FragmentQuizBinding, QuizViewModel>() {

    private val args: QuizFragmentArgs by navArgs()

    private val quizAdapter by lazy {
        QuizAdapter()
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

        binding.rvQuizzes.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = quizAdapter
        }

        quizAdapter.addOnItemClickListener {
            navController.navigate(QuizFragmentDirections.actionQuizFragmentToQuizDetailFragment(args.topic, args.section, it))
        }
    }

    override fun observeViewModel() {
        super.observeViewModel()

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.quizzes.observe(this@QuizFragment, { data ->
                quizAdapter.submitData(lifecycle, data)
            })
        }

        viewLifecycleOwner.lifecycleScope.launch {
            quizAdapter.loadStateFlow.collectLatest { loadStates ->
                binding.rvQuizzes.setVisibleOrGone(loadStates.refresh !is LoadState.Loading)
                binding.progressLoading.setVisibleOrGone(loadStates.refresh is LoadState.Loading)

                (loadStates.refresh as? LoadState.Error)?.let {
                    Errors.handle(this@QuizFragment.viewModel, it.error)
                }
            }
        }
    }

    override val layoutId: Int
        get() = R.layout.fragment_quiz
    override val viewModel: QuizViewModel
        get() = getViewModel { parametersOf(args.topic, args.section) }

}