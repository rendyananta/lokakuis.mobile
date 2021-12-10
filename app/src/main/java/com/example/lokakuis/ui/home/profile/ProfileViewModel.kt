package com.example.lokakuis.ui.home.profile

import com.example.lokakuis.base.architecture.AuthenticatedViewModel
import com.example.lokakuis.base.extensions.dispatchNow
import com.example.lokakuis.service.auth.RemoveSavedToken

class ProfileViewModel : AuthenticatedViewModel() {

    fun logout() {
        if (dispatchNow(RemoveSavedToken())) {
            setAuthenticated(false)
        }
    }
}