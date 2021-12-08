package com.example.lokakuis.entity.response.meta

import com.google.gson.annotations.SerializedName

data class Auth (
    @SerializedName("token")
    val token: String
)