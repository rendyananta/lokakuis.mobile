package com.example.lokakuis.ui.section

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.lokakuis.R
import com.example.lokakuis.base.lifecycleowner.AuthenticatedFragment
import com.example.lokakuis.databinding.FragmentSectionBinding
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf

class SectionFragment : AuthenticatedFragment<FragmentSectionBinding, SectionViewModel>() {

    private val args: SectionFragmentArgs by navArgs()

    override fun setupBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) {
        super.setupBinding(inflater, container, savedInstanceState)
        binding.viewModel = viewModel
    }

    override fun observeViewModel() {
        super.observeViewModel()
    }

    override val navigateToLoginId: Int
        get() = R.id.action_sectionFragment_to_loginFragment
    override val layoutId: Int
        get() = R.layout.fragment_section
    override val viewModel: SectionViewModel
        get() = getViewModel { parametersOf(args.topic) }

}