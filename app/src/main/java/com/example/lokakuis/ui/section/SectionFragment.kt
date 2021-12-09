package com.example.lokakuis.ui.section

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lokakuis.R
import com.example.lokakuis.base.lifecycleowner.AuthenticatedFragment
import com.example.lokakuis.base.view.GridSpacingItemDecoration
import com.example.lokakuis.databinding.FragmentSectionBinding
import com.example.lokakuis.ui.home.feed.FeedFragmentDirections
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf

class SectionFragment : AuthenticatedFragment<FragmentSectionBinding, SectionViewModel>() {

    private val args: SectionFragmentArgs by navArgs()

    private val sectionAdapter: SectionAdapter by lazy {
        SectionAdapter()
    }

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

        binding.rvSections.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = sectionAdapter
        }

        sectionAdapter.addOnItemClickListener {
            navController.navigate(SectionFragmentDirections.actionSectionFragmentToQuizFragment(args.topic, it))
        }
    }

    override fun observeViewModel() {
        super.observeViewModel()

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.sections.observe(this@SectionFragment, { data ->
                sectionAdapter.submitData(lifecycle, data)
            })
        }
    }

    override val layoutId: Int
        get() = R.layout.fragment_section
    override val viewModel: SectionViewModel
        get() = getViewModel { parametersOf(args.topic) }

}