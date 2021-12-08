package com.example.lokakuis.entity.response.topic

import com.example.lokakuis.entity.response.user.User
import com.google.gson.annotations.SerializedName

data class Topic(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("banner")
    val banner: String?,

    @SerializedName("description")
    val description: String?,

    @SerializedName("user_id")
    val userId: Int,

    @SerializedName("user")
    val user: User,

    @SerializedName("is_public")
    val isPublic: Boolean,

    @SerializedName("created_at")
    val createdAt: String,

    @SerializedName("updated_at")
    val updatedAt: String,

    @SerializedName("sections_count")
    val sectionsCount: Int,

    @SerializedName("quizzes_count")
    val quizzesCount: Int
)