package com.example.lokakuis.ui.home.feed

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.example.lokakuis.base.Constants
import com.example.lokakuis.base.architecture.AuthenticatedViewModel
import com.example.lokakuis.repository.topic.TopicPagingDataSource

class FeedViewModel : AuthenticatedViewModel() {

    val topics by lazy {
        Pager(
            config = PagingConfig(pageSize = Constants.PER_PAGE_SIZE, prefetchDistance = 2),
            pagingSourceFactory = { TopicPagingDataSource() }
        ).liveData.cachedIn(viewModelScope)
    }
}