package com.example.lokakuis.ui.home.feed

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.Navigation
import com.example.lokakuis.R
import com.example.lokakuis.base.extensions.dispatchNow
import com.example.lokakuis.base.lifecycleowner.BaseFragment
import com.example.lokakuis.databinding.FragmentFeedBinding
import com.example.lokakuis.service.auth.GetLoginStatus
import org.koin.androidx.viewmodel.ext.android.getViewModel

class FeedFragment : BaseFragment<FragmentFeedBinding, FeedViewModel>() {

    private val navController by lazy {
        Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
    }

    override fun onResume() {
        super.onResume()

        val loginStatus = dispatchNow(GetLoginStatus())

        if (! loginStatus) {
            redirectAuth()
        }
    }

    override fun observeViewModel() {

    }

    private fun redirectAuth() {
        navController.navigate(R.id.action_feedFragment_to_loginFragment)
    }

    override val layoutId: Int
        get() = R.layout.fragment_feed
    override val viewModel: FeedViewModel
        get() = getViewModel()
}