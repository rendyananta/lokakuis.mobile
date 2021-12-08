package com.example.lokakuis.entity.response.meta

import com.google.gson.annotations.SerializedName

data class PaginationLinks(
    @SerializedName("url")
    val url: String?,

    @SerializedName("label")
    val label: String,

    @SerializedName("active")
    val active: Boolean
)