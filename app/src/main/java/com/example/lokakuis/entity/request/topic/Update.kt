package com.example.lokakuis.entity.request.topic

data class Update(
    val name: String,
    val description: String,
    val isPublic: Boolean
)