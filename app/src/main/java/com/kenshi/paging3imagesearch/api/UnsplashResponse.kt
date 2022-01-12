package com.kenshi.paging3imagesearch.api

import com.kenshi.paging3imagesearch.data.model.UnsplashPhoto


data class UnsplashResponse (
    val results: List<UnsplashPhoto>
)