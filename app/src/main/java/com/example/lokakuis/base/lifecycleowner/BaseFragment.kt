package com.example.lokakuis.base.lifecycleowner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.Navigation
import com.example.lokakuis.R
import com.example.lokakuis.base.architecture.BaseViewModel
import com.example.lokakuis.base.architecture.HasObservableViewModel
import com.google.android.material.snackbar.Snackbar
import org.koin.core.component.KoinComponent

abstract class BaseFragment<T : ViewDataBinding, V : BaseViewModel> : Fragment(),
    HasObservableViewModel, LifecycleOwner, KoinComponent {

    lateinit var binding: T
    abstract val layoutId: Int
    abstract val viewModel: V

    protected val navController by lazy {
        Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        this.setupBinding(inflater, container, savedInstanceState)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        this.observeViewModelChanges()
    }

    open fun setupBinding(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding.lifecycleOwner = this
    }

    private fun observeViewModelChanges() {
        this.observeViewModel()
        this.onError()
    }

    private fun onError() {
        viewModel.onError(this) {
            val snackbar = Snackbar.make(activity?.findViewById(android.R.id.content) ?: binding.root, it.body, Snackbar.LENGTH_SHORT)

            when (it) {
                is BaseViewModel.Message.SuccessMessage -> {
                    snackbar.view.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.colorSuccess))
                }
                is BaseViewModel.Message.ErrorMessage -> {
                    snackbar.view.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.colorDanger))
                }
                is BaseViewModel.Message.WarningMessage -> {
                    snackbar.view.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.colorWarning))
                }
                is BaseViewModel.Message.InfoMessage -> {
                    snackbar.view.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.colorInfo))
                }
            }

            snackbar.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text).setTextColor(
                ContextCompat.getColor(requireContext(), R.color.colorTextThin)
            )

            snackbar.show()
        }
    }

}
