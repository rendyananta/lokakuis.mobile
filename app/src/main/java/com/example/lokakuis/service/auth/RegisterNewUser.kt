package com.example.lokakuis.service.auth

import android.util.Log
import com.example.lokakuis.base.extensions.dispatchNow
import com.example.lokakuis.base.service.AsynchronousService
import com.example.lokakuis.entity.request.auth.Register
import com.example.lokakuis.entity.response.Response
import com.example.lokakuis.entity.response.meta.Auth
import com.example.lokakuis.entity.response.user.User
import com.example.lokakuis.http.request.AuthApi
import org.koin.core.component.inject

class RegisterNewUser(
    private val name: String,
    private val email: String,
    private val password: String,
    private val passwordConfirmation: String
) : AsynchronousService<Response<User, Auth>>() {

    private val client: AuthApi by inject()

    override suspend fun runAsync(): Response<User, Auth> {
        val result = client.register(Register(name, email, password, passwordConfirmation, android.os.Build.MODEL))

        result.meta?.let { auth ->
            result.data?.let { user ->
                dispatchNow(SaveToken(auth.token, user))
            }
        } ?: run {
            Log.w("RequestToken", "meta response is empty")
        }

        return result
    }
}