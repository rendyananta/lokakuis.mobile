package com.example.lokakuis.entity.response.quiz

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Quiz(
    @SerializedName("id")
    val id: Int,

    @SerializedName("question")
    val question: String,

    @SerializedName("image_url")
    val image: String?,

    @SerializedName("answer")
    val answer: String,

    @SerializedName("topic_id")
    val topicId: Int,

    @SerializedName("section_id")
    val sectionId: Int,

    @SerializedName("created_at")
    val createdAt: String,

    @SerializedName("updated_at")
    val updatedAt: String
) : Parcelable