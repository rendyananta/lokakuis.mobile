package com.example.lokakuis.entity.response.section

import com.google.gson.annotations.SerializedName

data class Section (

    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("description")
    val description: String?,

    @SerializedName("topic_id")
    val topicId: Int,

    @SerializedName("quizzes_count")
    val quizzesCount: Int,

    @SerializedName("created_at")
    val createdAt: String,

    @SerializedName("updated_at")
    val updatedAt: String
)
