package com.example.lokakuis.base.lifecycleowner

import androidx.databinding.ViewDataBinding
import com.example.lokakuis.base.architecture.AuthenticatedViewModel
import com.example.lokakuis.base.extensions.dispatchNow
import com.example.lokakuis.service.auth.GetSavedUserProfile

abstract class AuthenticatedFragment<T : ViewDataBinding, V : AuthenticatedViewModel> : BaseFragment<T, V>() {

    abstract val navigateToLoginId: Int

    private val profile by lazy {
        dispatchNow(GetSavedUserProfile())
    }

    override fun onResume() {
        super.onResume()
    }

    override fun observeViewModel() {
        viewModel.authenticated.observe(this, {
            if (!it) {
                redirectAuth()
            } else {

            }
        })
    }

    private fun redirectAuth() {
        navController.navigate(navigateToLoginId)
    }
}