package com.example.lokakuis.entity.response.topic

import com.example.lokakuis.entity.response.user.User

data class Topic(
    val id: Int,
    val name: String,
    val banner: String?,
    val description: String?,
    val userId: Int,
    val user: User,
    val isPublic: Boolean,
    val sectionsCount: Int,
    val quizzesCount: Int
)