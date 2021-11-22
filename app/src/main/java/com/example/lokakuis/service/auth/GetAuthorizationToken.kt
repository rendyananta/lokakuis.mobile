package com.example.lokakuis.service.auth

import android.content.SharedPreferences
import com.example.lokakuis.base.Constants
import com.example.lokakuis.base.service.SynchronousService
import org.koin.core.component.inject

class GetAuthorizationToken : SynchronousService<String>() {

    private val preference: SharedPreferences by inject()

    override fun run(): String {
        return preference.getString(Constants.KEY_TOKEN, "") ?: ""
    }

}
