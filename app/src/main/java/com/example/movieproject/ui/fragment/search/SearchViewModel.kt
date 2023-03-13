package com.example.movieproject.ui.fragment.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieproject.data.api.ServiceInterface
import com.example.movieproject.data.uimodel.genre.GenreUiModel
import com.example.movieproject.data.uimodel.populars.PopularUiModel
import com.example.movieproject.data.usecase.discover.DiscoverUseCase
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
    private val discoverUseCase: DiscoverUseCase,
    private val serviceInterface: ServiceInterface,

) : ViewModel() {

    private val _searchAdapterList = MutableLiveData<ArrayList<PopularUiModel>>()
    val searchAdapterList : LiveData<ArrayList<PopularUiModel>> = _searchAdapterList
    private val _genredapterList = MutableLiveData<ArrayList<GenreUiModel>>()
    val genreAdapterList : LiveData<ArrayList<GenreUiModel>> = _genredapterList

    private val _discoverAdapterList = MutableLiveData<ArrayList<PopularUiModel>>()
    val discoverAdapterList : LiveData<ArrayList<PopularUiModel>> = _discoverAdapterList

    val editTextValue = MutableLiveData<String>()

    fun discover(discoverId:String){
        viewModelScope.launch {
            discoverUseCase.discoverId(discoverId)
            discoverUseCase.execute(
                onSuccess = {
                    popularsMapper.mapOnPopularsResponse(it)
                    _discoverAdapterList.postValue(popularsMapper.popularsAdapterList)
                },
                onError = {
                    it.printStackTrace()
                }
            )
        }

    }

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