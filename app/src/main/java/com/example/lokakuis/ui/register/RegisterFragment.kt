package com.example.lokakuis.ui.register

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import com.example.lokakuis.R
import com.example.lokakuis.base.lifecycleowner.BaseFragment
import com.example.lokakuis.databinding.FragmentRegisterBinding
import dev.poteto.formvalidator.Validator
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.component.inject

class RegisterFragment : BaseFragment<FragmentRegisterBinding, RegisterViewModel>() {

    private val formValidator: Validator by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.setupFormValidation()

        binding.btnLogin.setOnClickListener {
            this.setupFormValidation()
            if (this.formValidator.check()) {
                viewModel.registerNewUser(
                    binding.etName.text.toString(),
                    binding.etEmail.text.toString(),
                    binding.etPassword.text.toString(),
                    binding.etPasswordConfirmation.text.toString()
                )
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
            navController.navigate(R.id.action_loginFragment_to_feedFragment)
        })
    }

    override val layoutId: Int
        get() = R.layout.fragment_register
    override val viewModel: RegisterViewModel
        get() = getViewModel()
}