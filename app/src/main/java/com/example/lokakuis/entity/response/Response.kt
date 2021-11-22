package com.example.lokakuis.entity.response

data class Response<T, M>(
    val data: T?,
    val meta: M?,
    val links: Links,
    val errors: Map<String, List<String>>?,
    val message: String?
)