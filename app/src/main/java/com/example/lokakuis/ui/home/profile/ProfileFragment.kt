package com.example.lokakuis.ui.home.profile

import com.example.lokakuis.R
import com.example.lokakuis.base.lifecycleowner.BaseFragment
import com.example.lokakuis.databinding.FragmentProfileBinding
import org.koin.androidx.viewmodel.ext.android.getViewModel

class ProfileFragment : BaseFragment<FragmentProfileBinding, ProfileViewModel>() {
    override fun observeViewModel() {

    }

    override val layoutId: Int
        get() = R.layout.fragment_profile
    override val viewModel: ProfileViewModel
        get() = getViewModel()
}