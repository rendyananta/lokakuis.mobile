package com.example.lokakuis.entity.request.topic

data class Create(
    val name: String,
    val description: String,
    val isPublic: Boolean
)