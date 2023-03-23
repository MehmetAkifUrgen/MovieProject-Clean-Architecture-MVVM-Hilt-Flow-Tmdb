package com.example.movieproject.ui.fragment.search

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.example.movieproject.data.api.ServiceInterface
import com.example.movieproject.data.pagingdatasource.DiscoverPaging
import com.example.movieproject.data.uimodel.genre.GenreUiModel
import com.example.movieproject.data.uimodel.populars.PopularUiModel
import com.example.movieproject.data.usecase.genre.GenreUseCase
import com.example.movieproject.data.usecase.searchMovie.SearchMovieUseCase
import com.example.movieproject.ui.mapper.genre.GenreMapper
import com.example.movieproject.ui.mapper.populars.PopularsMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searcUseCase: SearchMovieUseCase,
    private val genreUseCase: GenreUseCase,
    private val genreMapper: GenreMapper,
    private val popularsMapper: PopularsMapper,
    private val serviceInterface: ServiceInterface,

) : ViewModel() {

    private val _searchAdapterList = MutableLiveData<ArrayList<PopularUiModel>>()
    val searchAdapterList : LiveData<ArrayList<PopularUiModel>> = _searchAdapterList
    private val _genredapterList = MutableLiveData<ArrayList<GenreUiModel>>()
    val genreAdapterList : LiveData<ArrayList<GenreUiModel>> = _genredapterList

    private val _discoverAdapterList = MutableLiveData<ArrayList<PopularUiModel>>()
    val discoverAdapterList : LiveData<ArrayList<PopularUiModel>> = _discoverAdapterList

    val editTextValue = MutableLiveData<String>()

    private val genre = MutableLiveData<String>()

    private val startDate = MutableLiveData<String>()

    private val endDate = MutableLiveData<String>()


    val list = genre.switchMap { query ->
        Pager(PagingConfig(pageSize = 10)) {
            startDate.value?.let { endDate.value?.let { it1 ->
                DiscoverPaging(query, it,
                    it1,serviceInterface)
            } }!!
        }.liveData.cachedIn(viewModelScope)
    }

    fun setQuery(s: String) {
        genre.postValue(s)
    }
    fun setStartDate(s: String) {
        startDate.postValue(s)
    }
    fun setEndDate(s: String) {
        endDate.postValue(s)
    }

//    fun discover(discoverId:String){
//        viewModelScope.launch {
//            discoverUseCase.discoverId(discoverId)
//            discoverUseCase.execute(
//                onSuccess = {
//                    popularsMapper.mapOnPopularsResponse(it)
//                    _discoverAdapterList.postValue(popularsMapper.popularsAdapterList)
//                },
//                onError = {
//                    it.printStackTrace()
//                }
//            )
//        }
//
//    }

     fun genreRequest(){
         viewModelScope.launch {
             genreUseCase.execute(
                 onSuccess = {
                     genreMapper.mapOnGenreResponse(it)
                     _genredapterList.postValue(genreMapper.genreAdapterList)
                 },
                 onError = {
                     it.printStackTrace()
                 }
             )
         }

    }

     fun castRequest(query:String) {
        viewModelScope.launch {
            searcUseCase.getQuery(query)
            searcUseCase.execute(
                onSuccess = {
                    popularsMapper.mapOnPopularsResponse(it)
                    _searchAdapterList.postValue(popularsMapper.popularsAdapterList)
                },
                onError = {
                    it.printStackTrace()
                }
            )
        }

    }
}