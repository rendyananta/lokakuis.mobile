package com.example.lokakuis.ui.home.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lokakuis.R
import com.example.lokakuis.base.lifecycleowner.AuthenticatedFragment
import com.example.lokakuis.databinding.FragmentProfileBinding
import org.koin.androidx.viewmodel.ext.android.getViewModel

class ProfileFragment : AuthenticatedFragment<FragmentProfileBinding, ProfileViewModel>() {

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

        binding.btnLogout.setOnClickListener {
            viewModel.logout()
        }
    }

    override val layoutId: Int
        get() = R.layout.fragment_profile
    override val viewModel: ProfileViewModel
        get() = getViewModel()
    override val navigateToLoginId: Int
        get() = R.id.action_profileFragment_to_loginFragment
}