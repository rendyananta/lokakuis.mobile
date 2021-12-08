package com.example.lokakuis.ui.login

import android.os.Bundle
import android.util.Log
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

    override val layoutId: Int
        get() = R.layout.fragment_login
    override val viewModel: LoginViewModel
        get() = getViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.setupFormValidation()

        binding.btnLogin.setOnClickListener {
            this.setupFormValidation()
            if (this.formValidator.check()) {
                viewModel.fetchAuthorizationToken(binding.etEmail.text.toString(), binding.etPassword.text.toString())
            }
        }

        binding.btnRegister.setOnClickListener {
            navController.navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    private fun setupFormValidation() {
        this.formValidator.reset()

        formValidator.buildRulesFor(binding.etEmail)
            .required()

        formValidator.buildRulesFor(binding.etPassword)
            .required()
            .min(6)
    }

    override fun observeViewModel() {
        viewModel.loginResult.observe(this, {
            Log.wtf("AUTHENTICATED", it.toString())
            navController.popBackStack()
        })
    }
}