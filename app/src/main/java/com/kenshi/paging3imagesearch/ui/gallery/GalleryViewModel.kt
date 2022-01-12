package com.kenshi.paging3imagesearch.ui.gallery

import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.kenshi.paging3imagesearch.data.network.UnsplashRepository

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val repository: UnsplashRepository,
    //@Assisted annotation is no longer needed in front of SavedStateHandle
    //@Assisted state: SavedStateHandle
    state: SavedStateHandle
    ): ViewModel() {

    private val currentQuery = state.getLiveData(CURRENT_QUERY, DEFAULT_QUERY)
    //private val currentQuery = MutableLiveData(DEFAULT_QUERY)

    val photos = currentQuery.switchMap { queryString ->
        repository.getSearchResults(queryString).cachedIn(viewModelScope)
    }

    //val photos = repository.getSearchResults("cats")

    fun searchPhotos(query: String) {
        currentQuery.value = query
    }

    companion object {
        private const val CURRENT_QUERY = "current_query"
        private const val DEFAULT_QUERY= "cats"
    }
}