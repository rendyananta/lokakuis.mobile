package com.example.lokakuis.http.request

import com.example.lokakuis.base.Constants
import com.example.lokakuis.entity.response.Response
import com.example.lokakuis.entity.response.meta.Pagination
import com.example.lokakuis.entity.response.quiz.Quiz
import com.example.lokakuis.entity.response.section.Section
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface QuizApi {

    @GET("/api/topic/{topic_id}/section/{section_id}/quiz")
    suspend fun index(
        @Path("topic_id") topicId: Int,
        @Path("section_id") sectionId: Int,
        @Query("page") page: Int? = 1,
        @Query("per_page") perPage: Int? = Constants.PER_PAGE_SIZE,
        @Query("keyword") keyword: String? = "",
    ): Response<List<Quiz>, Pagination>

    @GET("/api/topic/{topic_id}/section/{section_id}/quiz/{quiz_id}")
    suspend fun detail(
        @Path("topic_id") topicId: Int,
        @Path("section_id") sectionId: Int,
        @Path("quiz_id") quizId: Int,
    ): Response<Quiz, Pagination>
}