package com.example.lokakuis.http.request

import com.example.lokakuis.base.Constants
import com.example.lokakuis.entity.response.Response
import com.example.lokakuis.entity.response.meta.Pagination
import com.example.lokakuis.entity.response.topic.Topic
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TopicApi {

    @GET("/api/topic")
    suspend fun index(
        @Query("page") page: Int? = 1,
        @Query("per_page") perPage: Int? = Constants.PER_PAGE_SIZE,
        @Query("keyword") keyword: String? = "",
    ): Response<List<Topic>, Pagination>

    @GET("/api/topic/{topic_id}")
    suspend fun detail(@Path("topic_id") topicId: Int): Response<Topic, String>
}