package com.example.lokakuis.entity.request.auth

data class Login(
    val email: String,
    val password: String,
    val deviceName: String
)