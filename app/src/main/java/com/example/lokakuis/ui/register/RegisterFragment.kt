package com.example.lokakuis.ui.register

import com.example.lokakuis.R
import com.example.lokakuis.base.lifecycleowner.BaseFragment
import com.example.lokakuis.databinding.FragmentRegisterBinding
import org.koin.androidx.viewmodel.ext.android.getViewModel

class RegisterFragment : BaseFragment<FragmentRegisterBinding, RegisterViewModel>() {

    override fun observeViewModel() {

    }

    override val layoutId: Int
        get() = R.layout.fragment_register
    override val viewModel: RegisterViewModel
        get() = getViewModel()
}