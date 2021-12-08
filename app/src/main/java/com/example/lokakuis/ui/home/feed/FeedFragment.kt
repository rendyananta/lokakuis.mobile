package com.example.lokakuis.ui.home.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.lokakuis.R
import com.example.lokakuis.base.lifecycleowner.AuthenticatedFragment
import com.example.lokakuis.base.view.GridSpacingItemDecoration
import com.example.lokakuis.databinding.FragmentFeedBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.getViewModel

class FeedFragment : AuthenticatedFragment<FragmentFeedBinding, FeedViewModel>() {

    private val topicsAdapter by lazy {
        TopicAdapter()
    }

    override fun setupBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) {
        super.setupBinding(inflater, container, savedInstanceState)

        binding.viewModel = viewModel
    }

    override fun onResume() {
        super.onResume()

        binding.rvTopics.apply {
            layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
            addItemDecoration(GridSpacingItemDecoration(2, 16, false))
            adapter = topicsAdapter
        }
    }

    override fun observeViewModel() {
        super.observeViewModel()

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.topics.observe(this@FeedFragment, { data ->
                topicsAdapter.submitData(lifecycle, data)
            })
        }
    }

    override val layoutId: Int
        get() = R.layout.fragment_feed
    override val viewModel: FeedViewModel
        get() = getViewModel()
    override val navigateToLoginId: Int
        get() = R.id.action_feedFragment_to_loginFragment
}