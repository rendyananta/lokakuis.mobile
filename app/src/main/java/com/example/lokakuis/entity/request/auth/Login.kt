package com.example.lokakuis.entity.request.auth

import com.google.gson.annotations.SerializedName

data class Login(

    @SerializedName("email")
    val email: String,

    @SerializedName("password")
    val password: String,

    @SerializedName("device_name")
    val deviceName: String
)