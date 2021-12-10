package com.example.lokakuis.entity.request.auth

import com.google.gson.annotations.SerializedName

data class UpdateProfile(

    @SerializedName("name")
    val name: String,

    @SerializedName("email")
    val email: String
)