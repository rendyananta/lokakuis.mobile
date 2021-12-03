package com.example.lokakuis.ui.login

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import com.example.lokakuis.R
import com.example.lokakuis.base.lifecycleowner.BaseFragment
import com.example.lokakuis.databinding.FragmentLoginBinding
import dev.poteto.formvalidator.Validator
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.component.inject

class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {

    private val formValidator: Validator by inject()

    private val navController by lazy {
        Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
    }

    override val layoutId: Int
        get() = R.layout.fragment_login
    override val viewModel: LoginViewModel
        get() = getViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.setupFormValidation()

        binding.btnLogin.setOnClickListener {
            formValidator.reset()
            if (this.formValidator.check()) {
                viewModel.fetchAuthorizationToken(binding.etEmail.text.toString(), binding.etPassword.text.toString())
            }
        }
    }

    private fun setupFormValidation() {
        formValidator.buildRulesFor(binding.etEmail)
            .required()

        formValidator.buildRulesFor(binding.etPassword)
            .required()
            .min(6)
    }

    override fun observeViewModel() {
        viewModel.loginResult.observe(this, {
            navController.navigate(R.id.nav_account)
        })
    }
}