package com.example.lokakuis.entity.request.auth

import com.google.gson.annotations.SerializedName

data class Register(
    @SerializedName("name")
    val name: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("password")
    val password: String,

    @SerializedName("password_confirmation")
    val passwordConfirmation: String
)