package com.example.lokakuis.repository.quiz

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.lokakuis.base.Constants
import com.example.lokakuis.entity.request.quiz.Param
import com.example.lokakuis.entity.response.quiz.Quiz
import com.example.lokakuis.entity.response.section.Section
import com.example.lokakuis.entity.response.topic.Topic
import com.example.lokakuis.http.request.QuizApi
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.HttpException
import java.io.IOException

class QuizPagingDataSource(
    private val topic: Topic,
    private val section: Section,
    private val keyword: String = ""
) : PagingSource<Param, Quiz>(), KoinComponent {

    private val quizApi: QuizApi by inject()

    override fun getRefreshKey(state: PagingState<Param, Quiz>): Param? {
        return state.anchorPosition?.let {
            val page = state.closestPageToPosition(it)?.prevKey?.page?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.page?.minus(1)

            page?.let { p ->
                Param(
                    p,
                    Constants.PER_PAGE_SIZE,
                    keyword
                )
            } ?: Param(
                1,
                Constants.PER_PAGE_SIZE,
                keyword
            )
        }
    }

    override suspend fun load(params: LoadParams<Param>): LoadResult<Param, Quiz> {
        return try {
            val result = quizApi.index(topic.id, section.id, params.key?.page, params.key?.perPage, params.key?.keyword)

            val currentPage = params.key?.page ?: 1
            val totalPage = params.key?.totalPage ?: 1

            val prevKey = if (currentPage > 1) Param() else null

            val nextKey = if (currentPage < totalPage) Param(
                currentPage,
                Constants.PER_PAGE_SIZE,
                keyword,
                totalPage
            ) else null

            result.data?.let {
                LoadResult.Page(it, prevKey, nextKey)
            } ?: LoadResult.Page(listOf(), prevKey, nextKey)
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}