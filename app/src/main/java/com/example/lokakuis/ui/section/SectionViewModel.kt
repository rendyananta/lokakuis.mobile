package com.example.lokakuis.ui.section

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.example.lokakuis.base.Constants
import com.example.lokakuis.base.architecture.AuthenticatedViewModel
import com.example.lokakuis.entity.response.topic.Topic
import com.example.lokakuis.repository.section.SectionPagingDataSource

class SectionViewModel(val topic: Topic) : AuthenticatedViewModel() {

    val sections by lazy {
        Pager(
            config = PagingConfig(pageSize = Constants.PER_PAGE_SIZE, prefetchDistance = 2),
            pagingSourceFactory = { SectionPagingDataSource(topic) }
        ).liveData.cachedIn(viewModelScope)
    }

}