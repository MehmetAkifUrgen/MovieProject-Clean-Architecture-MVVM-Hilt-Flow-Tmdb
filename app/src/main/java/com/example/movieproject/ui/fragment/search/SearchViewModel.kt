package com.example.movieproject.ui.fragment.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieproject.data.uimodel.cast.CastDetailUiModel
import com.example.movieproject.data.uimodel.cast.CrewDetailUiModel
import com.example.movieproject.data.uimodel.populars.PopularUiModel
import com.example.movieproject.data.usecase.cast.CastUseCase
import com.example.movieproject.data.usecase.searchMovie.SearchMovieUseCase
import com.example.movieproject.ui.mapper.cast.CastMapper
import com.example.movieproject.ui.mapper.populars.PopularsMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.http.Query
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searcUseCase: SearchMovieUseCase,
    private val popularsMapper: PopularsMapper,
) : ViewModel() {

    private val _searchAdapterList = MutableLiveData<ArrayList<PopularUiModel>>()
    val searchAdapterList : LiveData<ArrayList<PopularUiModel>> = _searchAdapterList

    val editTextValue = MutableLiveData<String>()










    fun castRequest(query:String) {
        searcUseCase.getQuery(query)
        searcUseCase.execute(
            onSuccess = {
                popularsMapper.mapOnAgentsResponse(it)
                _searchAdapterList.value = popularsMapper.agentsAdapterList
            },
            onError = {
                it.printStackTrace()
            }
        )
    }
}