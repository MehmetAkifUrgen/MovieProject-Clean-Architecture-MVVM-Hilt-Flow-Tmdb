package com.example.movieproject.ui.fragment.populars

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.example.movieproject.data.api.ServiceInterface
import com.example.movieproject.data.pagingdatasource.DiscoverPaging
import com.example.movieproject.data.pagingdatasource.MoviePaging
import com.example.movieproject.data.pagingdatasource.RecommedPaging
import com.example.movieproject.data.response.watch.WatchResponse
import com.example.movieproject.data.response.watch.WatchResultResponse
import com.example.movieproject.data.uimodel.populars.PopularDetailsUiModel
import com.example.movieproject.data.usecase.populars.PopularDetailUseCase
import com.example.movieproject.ui.mapper.populars.PopularDetailMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val agentDetailUseCase: PopularDetailUseCase,
    private val agentDetailMapper: PopularDetailMapper,
    private val serviceInterface: ServiceInterface
) : ViewModel() {

    private val _agentDetailItem = MutableLiveData<PopularDetailsUiModel>()
    val agentDetailItem: LiveData<PopularDetailsUiModel> = _agentDetailItem
    private val movieId = MutableLiveData<String>()


    fun setMovieId(s: String) {
        movieId.postValue(s)
    }

    val recommedList = movieId.switchMap { query ->
        Pager(PagingConfig(pageSize = 20)) {
            RecommedPaging(query, serviceInterface)
        }.liveData.cachedIn(viewModelScope)
    }

    fun agentDetailRequest(id: String) {
        viewModelScope.launch {
            agentDetailUseCase.popularId(id)
            agentDetailUseCase.execute(
                onSuccess = {
                    agentDetailMapper.mapOnPopularDetailResponse(it)
                    _agentDetailItem.postValue(agentDetailMapper.popularDetailItem)
                },
                onError = {
                    it.printStackTrace()
                }
            )
        }
    }
}