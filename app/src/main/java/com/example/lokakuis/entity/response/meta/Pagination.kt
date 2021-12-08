package com.example.lokakuis.entity.response.meta

import com.google.gson.annotations.SerializedName

data class Pagination(

    @SerializedName("current_page")
    val currentPage: Int,

    @SerializedName("from")
    val from: Int?,

    @SerializedName("last_page")
    val lastPage: Int,

    @SerializedName("links")
    val links: List<PaginationLinks>,

    @SerializedName("path")
    val path: String,

    @SerializedName("per_page")
    val perPage: Int,

    @SerializedName("to")
    val to: Int,

    @SerializedName("total")
    val total: Int
)