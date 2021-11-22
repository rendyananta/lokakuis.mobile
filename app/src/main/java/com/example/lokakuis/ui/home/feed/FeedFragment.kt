package com.example.lokakuis.ui.home.feed

import com.example.lokakuis.R
import com.example.lokakuis.base.lifecycleowner.BaseFragment
import com.example.lokakuis.databinding.FragmentFeedBinding
import org.koin.androidx.viewmodel.ext.android.getViewModel

class FeedFragment : BaseFragment<FragmentFeedBinding, FeedViewModel>() {
    override fun observeViewModel() {

    }

    override val layoutId: Int
        get() = R.layout.fragment_feed
    override val viewModel: FeedViewModel
        get() = getViewModel()
}