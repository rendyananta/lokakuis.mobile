package com.example.lokakuis.service.user

import android.util.Log
import com.example.lokakuis.base.extensions.dispatchNow
import com.example.lokakuis.base.service.AsynchronousService
import com.example.lokakuis.entity.request.auth.Register
import com.example.lokakuis.entity.response.Response
import com.example.lokakuis.entity.response.meta.Auth
import com.example.lokakuis.entity.response.user.User
import com.example.lokakuis.http.request.AuthRequest
import com.example.lokakuis.service.auth.SaveToken
import org.koin.core.component.inject

class RegisterNewUser(
    private val name: String,
    private val email: String,
    private val password: String,
    private val passwordConfirmation: String
) : AsynchronousService<Response<User, Auth>>() {

    private val client: AuthRequest by inject()

    override suspend fun runAsync(): Response<User, Auth> {
        val result = client.register(Register(name, email, password, passwordConfirmation))

        result.meta?.let {
            dispatchNow(SaveToken(it.token))
        } ?: run {
            Log.w("RequestToken", "meta response is empty")
        }

        return result
    }
}