package com.example.lokakuis.entity.response.meta

data class Pagination(
    val currentPage: Int,
    val from: Int?,
    val lastPage: Int,
    val links: PaginationLinks,
    val path: String,
    val perPage: Int,
    val to: Int,
    val total: Int
)