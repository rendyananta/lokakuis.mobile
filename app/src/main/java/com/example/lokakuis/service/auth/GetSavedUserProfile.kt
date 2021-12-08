package com.example.lokakuis.service.auth

import android.content.SharedPreferences
import com.example.lokakuis.base.Constants
import com.example.lokakuis.base.extensions.fromJson
import com.example.lokakuis.base.service.SynchronousService
import com.example.lokakuis.entity.response.user.User
import com.google.gson.Gson
import org.koin.core.component.inject

class GetSavedUserProfile : SynchronousService<User>() {

    private val preference: SharedPreferences by inject()
    private val gson: Gson by inject()

    override fun run(): User {
        val json = preference.getString(Constants.KEY_PROFILE, "") ?: ""

        return gson.fromJson<User>(json)
    }
}