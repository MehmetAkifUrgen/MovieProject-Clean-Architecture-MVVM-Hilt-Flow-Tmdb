package com.example.movieproject.ui.fragment.populars

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.example.movieproject.data.api.ServiceInterface
import com.example.movieproject.data.pagingdatasource.MoviePaging
import com.example.movieproject.data.pagingdatasource.UpComingPaging
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val serviceInterface: ServiceInterface
) : ViewModel() {

    val popularlist = Pager(PagingConfig(pageSize = 20)) {
            MoviePaging(serviceInterface)
        }.liveData.cachedIn(viewModelScope)

    val upCominglist = Pager(PagingConfig(pageSize = 20)) {
        UpComingPaging(serviceInterface)
    }.liveData.cachedIn(viewModelScope)

}