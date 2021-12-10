package com.example.lokakuis.service.auth

import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.lokakuis.base.Constants
import com.example.lokakuis.base.service.AsynchronousService
import com.example.lokakuis.entity.request.auth.UpdateProfile
import com.example.lokakuis.entity.response.Response
import com.example.lokakuis.entity.response.user.User
import com.example.lokakuis.http.request.ProfileApi
import com.google.gson.Gson
import org.koin.core.component.inject

class UpdateUserProfile(
    private val email: String,
    private val name: String
) : AsynchronousService<Response<User, String>>() {

    private val profileApi: ProfileApi by inject()

    private val preference: SharedPreferences by inject()
    private val gson: Gson by inject()

    override suspend fun runAsync(): Response<User, String> {
        val result = profileApi.updateProfile(UpdateProfile(name, email))

        val userJson = gson.toJson(result.data)

        preference.edit {
            putString(Constants.KEY_PROFILE, userJson)
        }

        return result
    }
}