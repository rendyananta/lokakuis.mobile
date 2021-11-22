package com.example.lokakuis.entity.request.auth

data class Register(
    val name: String,
    val email: String,
    val password: String,
    val passwordConfirmation: String
)