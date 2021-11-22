package com.example.lokakuis.ui.login

import com.example.lokakuis.R
import com.example.lokakuis.base.lifecycleowner.BaseFragment
import com.example.lokakuis.databinding.FragmentLoginBinding
import org.koin.androidx.viewmodel.ext.android.getViewModel

class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {
    override fun observeViewModel() {

    }

    override val layoutId: Int
        get() = R.layout.fragment_login
    override val viewModel: LoginViewModel
        get() = getViewModel()
}