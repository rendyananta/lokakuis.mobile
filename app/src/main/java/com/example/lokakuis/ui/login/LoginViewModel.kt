package com.example.lokakuis.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.lokakuis.base.architecture.BaseViewModel
import com.example.lokakuis.base.extensions.dispatch
import com.example.lokakuis.base.extensions.dispatchNow
import com.example.lokakuis.base.extensions.safeCall
import com.example.lokakuis.service.auth.GetLoginStatus
import com.example.lokakuis.service.auth.RequestToken

class LoginViewModel : BaseViewModel() {

    private val _loginResult: MutableLiveData<Boolean> = MutableLiveData()
    val loginResult: LiveData<Boolean> = _loginResult

    fun fetchAuthorizationToken(email: String, password: String) {
        safeCall {
            val result = dispatch(RequestToken(email, password))

            if (! result.meta?.token.isNullOrEmpty()) {
                _loginResult.value = true
            }
        }
    }
}