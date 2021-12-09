package com.example.lokakuis.http.request

import com.example.lokakuis.base.Constants
import com.example.lokakuis.entity.response.Response
import com.example.lokakuis.entity.response.meta.Pagination
import com.example.lokakuis.entity.response.section.Section
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SectionApi {

    @GET("/api/topic/{topic_id}/section")
    suspend fun index(
        @Path("topic_id") topicId: Int,
        @Query("page") page: Int? = 1,
        @Query("per_page") perPage: Int? = Constants.PER_PAGE_SIZE,
        @Query("keyword") keyword: String? = "",
    ): Response<List<Section>, Pagination>

    @GET("/api/topic/{topic_id}/section/{section_id}")
    suspend fun detail(
        @Path("topic_id") topicId: Int,
        @Path("section_id") sectionId: Int,
    ): Response<List<Section>, Pagination>
}