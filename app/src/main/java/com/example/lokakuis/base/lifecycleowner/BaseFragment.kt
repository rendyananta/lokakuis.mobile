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
import com.example.lokakuis.R
import com.example.lokakuis.base.architecture.BaseViewModel
import com.example.lokakuis.base.architecture.HasObservableViewModel
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment<T : ViewDataBinding, V : BaseViewModel> : Fragment(),
    HasObservableViewModel, LifecycleOwner {

    lateinit var binding: T
    abstract val layoutId: Int
    abstract val viewModel: V

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        this.setupBinding(inflater, container, savedInstanceState)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
