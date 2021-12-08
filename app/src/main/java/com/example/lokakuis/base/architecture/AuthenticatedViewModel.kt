package com.example.lokakuis.base.architecture

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.lokakuis.base.extensions.dispatchNow
import com.example.lokakuis.entity.response.user.User
import com.example.lokakuis.service.auth.GetLoginStatus
import com.example.lokakuis.service.auth.GetSavedUserProfile


open class AuthenticatedViewModel : BaseViewModel() {

    protected val _authenticated: MutableLiveData<Boolean> = MutableLiveData()
    val authenticated: LiveData<Boolean> = _authenticated

    private val _profile: MutableLiveData<User> = MutableLiveData()
    val profile: LiveData<User> = _profile

    init {
        _authenticated.value = this.checkLoginStatus()

        if (_authenticated.value == true) {
            this.getUserProfile()
        }
    }

    private fun checkLoginStatus(): Boolean = dispatchNow(GetLoginStatus())

    private fun getUserProfile() {
        this._profile.value = dispatchNow(GetSavedUserProfile())
    }
}
