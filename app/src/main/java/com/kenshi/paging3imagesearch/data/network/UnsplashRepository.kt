package com.kenshi.paging3imagesearch.data.network

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.kenshi.paging3imagesearch.api.UnsplashApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UnsplashRepository @Inject constructor(private val unsplashApi: UnsplashApi){

    fun getSearchResults(query: String) =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { UnsplashPagingSource(unsplashApi, query) }
        ).liveData
}