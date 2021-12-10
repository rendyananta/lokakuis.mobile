package com.example.lokakuis.ui.home.profile.edit

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.lokakuis.R
import com.example.lokakuis.activity.MainActivity
import com.example.lokakuis.base.Constants
import com.example.lokakuis.base.architecture.BaseViewModel
import com.example.lokakuis.base.lifecycleowner.AuthenticatedFragment
import com.example.lokakuis.databinding.FragmentEditProfileBinding
import dev.poteto.formvalidator.Validator
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.component.inject

class EditProfileFragment : AuthenticatedFragment<FragmentEditProfileBinding, EditProfileViewModel>() {

    private val formValidator: Validator by inject()

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

        binding.btnSave.setOnClickListener {
            this.setupFormValidation()

            if (this.formValidator.check()) {
                viewModel.update(binding.etEmail.text.toString(), binding.etName.text.toString())
            }
        }
    }

    private fun setupFormValidation() {
        this.formValidator.reset()

        formValidator.buildRulesFor(binding.etEmail)
            .required()

        formValidator.buildRulesFor(binding.etName)
            .required()
    }

    override fun observeViewModel() {
        super.observeViewModel()

        this.viewModel.status.observe(this, {
            if (it) {
                reload()
            }
        })
    }

    private fun reload() {
        startActivity(
            Intent(requireActivity(), MainActivity::class.java).apply {
                putExtra(Constants.INTENT_NAME_MESSAGE, BaseViewModel.Message.SuccessMessage("Profil berhasil diubah"))
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
        )
    }

    override val layoutId: Int
        get() = R.layout.fragment_edit_profile
    override val viewModel: EditProfileViewModel
        get() = getViewModel()
}