package com.example.lokakuis.http.request

import com.example.lokakuis.entity.request.auth.UpdateProfile
import com.example.lokakuis.entity.response.Response
import com.example.lokakuis.entity.response.user.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT

interface ProfileApi {

    @GET("api/auth/me")
    suspend fun getProfile(): Response<User, String>

    @PUT("api/auth/me")
    suspend fun updateProfile(@Body updateProfileBody: UpdateProfile): Response<User, String>
}