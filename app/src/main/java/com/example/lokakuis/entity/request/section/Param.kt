package com.example.lokakuis.entity.request.section

import com.example.lokakuis.base.Constants

data class Param(
    val page: Int? = 1,
    val perPage: Int? = Constants.PER_PAGE_SIZE,
    val keyword: String? = "",
    val totalPage: Int? = 1
)