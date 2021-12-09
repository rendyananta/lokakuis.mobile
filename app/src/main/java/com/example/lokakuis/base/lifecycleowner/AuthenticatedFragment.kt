package com.example.lokakuis.base.lifecycleowner

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.ViewDataBinding
import com.example.lokakuis.activity.AuthActivity
import com.example.lokakuis.base.architecture.AuthenticatedViewModel
import com.example.lokakuis.base.extensions.dispatchNow
import com.example.lokakuis.service.auth.GetSavedUserProfile

abstract class AuthenticatedFragment<T : ViewDataBinding, V : AuthenticatedViewModel> : BaseFragment<T, V>() {

    private val profile by lazy {
        dispatchNow(GetSavedUserProfile())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun observeViewModel() {
        viewModel.authenticated.observe(this, {
            if (!it) {
                redirectAuth()
            }
        })
    }

    private fun redirectAuth() {
        val intent = Intent(requireActivity(), AuthActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

        startActivity(intent)
    }
}