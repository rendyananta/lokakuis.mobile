package com.example.lokakuis.ui.quiz

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.example.lokakuis.base.Constants
import com.example.lokakuis.base.architecture.AuthenticatedViewModel
import com.example.lokakuis.entity.response.section.Section
import com.example.lokakuis.entity.response.topic.Topic
import com.example.lokakuis.repository.quiz.QuizPagingDataSource
import com.example.lokakuis.repository.section.SectionPagingDataSource

class QuizViewModel(val topic: Topic, val section: Section) : AuthenticatedViewModel() {

    val quizzes by lazy {
        Pager(
            config = PagingConfig(pageSize = Constants.PER_PAGE_SIZE, prefetchDistance = 2),
            pagingSourceFactory = { QuizPagingDataSource(topic, section) }
        ).liveData.cachedIn(viewModelScope)
    }

}