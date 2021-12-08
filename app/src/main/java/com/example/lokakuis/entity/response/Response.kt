package com.example.lokakuis.entity.response

import com.google.gson.annotations.SerializedName

data class Response<T, M>(

    @SerializedName("data")
    val data: T?,

    @SerializedName("meta")
    val meta: M?,

    @SerializedName("links")
    val links: Links,

    @SerializedName("errors")
    val errors: Map<String, List<String>>?,

    @SerializedName("message")
    val message: String?
)