package com.example.lokakuis.base.architecture

import androidx.lifecycle.MutableLiveData


open class AuthenticatedViewModel : BaseViewModel() {

    private val _loginStatus: MutableLiveData<Boolean> = MutableLiveData()

    init {
        _loginStatus.value = this.checkLoginStatus()
    }

    fun checkLoginStatus(): Boolean = true
}
