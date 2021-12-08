package com.example.lokakuis.http.request

import com.example.lokakuis.entity.response.Response
import com.example.lokakuis.entity.response.meta.Pagination
import com.example.lokakuis.entity.response.section.Section
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface QuizApi {

    @GET("/api/topic/{topic_id}/section/{section_id}/quiz")
    fun index(
        @Path("topic_id") topicId: Int,
        @Path("section_id") sectionId: Int,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        @Query("keyword") keyword: Int,
    ): Response<List<Section>, Pagination>

    @GET("/api/topic/{topic_id}/section/{section_id}/quiz/{quiz_id}")
    fun detail(
        @Path("topic_id") topicId: Int,
        @Path("section_id") sectionId: Int,
        @Path("quiz_id") quizId: Int,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        @Query("keyword") keyword: Int,
    ): Response<List<Section>, Pagination>
}