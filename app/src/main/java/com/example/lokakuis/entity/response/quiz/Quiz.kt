package com.example.lokakuis.entity.response.quiz

import com.google.gson.annotations.SerializedName

data class Quiz(
    @SerializedName("id")
    val id: Int,

    @SerializedName("question")
    val question: String,

    @SerializedName("image")
    val image: String?,

    @SerializedName("question")
    val answer: String,

    @SerializedName("topic_id")
    val topicId: Int,

    @SerializedName("section_id")
    val sectionId: Int,

    @SerializedName("created_at")
    val createdAt: String,

    @SerializedName("updated_at")
    val updatedAt: String
)