package com.example.lokakuis.ui.register

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import com.example.lokakuis.R
import com.example.lokakuis.activity.MainActivity
import com.example.lokakuis.base.Constants
import com.example.lokakuis.base.architecture.BaseViewModel
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

        binding.btnRegister.setOnClickListener {
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

        binding.btnLogin.setOnClickListener {
            navController.navigate(R.id.action_registerFragment_to_loginFragment)
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
            if (it) {
                startActivity(
                    Intent(requireActivity(), MainActivity::class.java).apply {
                        putExtra(Constants.INTENT_NAME_MESSAGE, BaseViewModel.Message.SuccessMessage("Berhasil Mendaftar"))
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    }
                )
            }
        })
    }

    override val layoutId: Int
        get() = R.layout.fragment_register
    override val viewModel: RegisterViewModel
        get() = getViewModel()
}