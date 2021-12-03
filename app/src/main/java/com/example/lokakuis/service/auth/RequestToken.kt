package com.example.lokakuis.service.auth

import android.util.Log
import com.example.lokakuis.base.extensions.dispatchNow
import com.example.lokakuis.base.service.AsynchronousService
import com.example.lokakuis.entity.request.auth.Login
import com.example.lokakuis.entity.response.Response
import com.example.lokakuis.entity.response.meta.Auth
import com.example.lokakuis.http.request.AuthContract
import org.koin.core.component.inject

class RequestToken(
    private val email: String,
    private val password: String,
) : AsynchronousService<Response<String, Auth>>() {

    private val client: AuthContract by inject()

    override suspend fun runAsync(): Response<String, Auth> {
        val result = client.login(Login(email, password, android.os.Build.MODEL))

        result.meta?.let {
            dispatchNow(SaveToken(it.token))
        } ?: run {
            Log.w("RequestToken", "meta response is empty")
        }

        return result
    }
}