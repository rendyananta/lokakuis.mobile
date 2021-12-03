package com.example.lokakuis.http.request

import com.example.lokakuis.entity.request.auth.Login
import com.example.lokakuis.entity.request.auth.Register
import com.example.lokakuis.entity.response.Response
import com.example.lokakuis.entity.response.meta.Auth
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthContract {

    @POST("/api/login")
    suspend fun login(@Body loginBody: Login): Response<String, Auth>

    @POST("/api/register")
    suspend fun register(@Body registerBody: Register): Response<String, Auth>

}