package com.example.lokakuis.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.lokakuis.base.architecture.BaseViewModel
import com.example.lokakuis.base.extensions.dispatch
import com.example.lokakuis.base.extensions.dispatchNow
import com.example.lokakuis.base.extensions.safeCall
import com.example.lokakuis.service.auth.GetLoginStatus
import com.example.lokakuis.service.auth.RegisterNewUser

class RegisterViewModel : BaseViewModel() {

    private val _loginResult: MutableLiveData<Boolean> = MutableLiveData()
    val loginResult: LiveData<Boolean> = _loginResult

    fun registerNewUser(name: String, email: String, password: String, passwordConfirmation: String) {
        safeCall {
            val result = dispatch(RegisterNewUser(name, email, password, passwordConfirmation))

            if (! result.meta?.token.isNullOrEmpty()) {
                _loginResult.value = true
            }
        }
    }
}