package com.example.lokakuis.ui.home.profile.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.lokakuis.base.architecture.AuthenticatedViewModel
import com.example.lokakuis.base.extensions.dispatch
import com.example.lokakuis.base.extensions.safeCall
import com.example.lokakuis.service.auth.UpdateUserProfile

class EditProfileViewModel : AuthenticatedViewModel() {

    private val _status: MutableLiveData<Boolean> = MutableLiveData()
    val status: LiveData<Boolean> = _status

    init {
        if (authenticated.value == true) {
            this.getUserProfile()
        }
    }

    fun update(email: String, name: String) {
        safeCall {
            _status.value = false
            val result = dispatch(UpdateUserProfile(email, name)).data
            setProfile(result)

        }

        _status.value = true
    }
}