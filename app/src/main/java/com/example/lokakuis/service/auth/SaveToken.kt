package com.example.lokakuis.service.auth

import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.lokakuis.base.Constants
import com.example.lokakuis.base.service.SynchronousService
import com.example.lokakuis.entity.response.user.User
import com.google.gson.Gson
import org.koin.core.component.inject

class SaveToken(private val token: String, private val user: User) : SynchronousService<Boolean>() {

    private val preference: SharedPreferences by inject()
    private val gson: Gson by inject()

    override fun run(): Boolean {

        val userContents = gson.toJson(user)

        preference.edit {
            putString(Constants.KEY_TOKEN, token)
            putString(Constants.KEY_PROFILE, userContents)
        }

        return true
    }
}