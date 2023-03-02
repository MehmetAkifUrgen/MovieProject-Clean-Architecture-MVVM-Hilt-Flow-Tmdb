package com.example.movieproject.ui.fragment.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieproject.data.uimodel.cast.CastDetailUiModel
import com.example.movieproject.data.uimodel.cast.CrewDetailUiModel
import com.example.movieproject.data.uimodel.genre.GenreUiModel
import com.example.movieproject.data.uimodel.populars.PopularUiModel
import com.example.movieproject.data.usecase.cast.CastUseCase
import com.example.movieproject.data.usecase.discover.DiscoverUseCase
import com.example.movieproject.data.usecase.genre.GenreUseCase
import com.example.movieproject.data.usecase.searchMovie.SearchMovieUseCase
import com.example.movieproject.ui.mapper.cast.CastMapper
import com.example.movieproject.ui.mapper.genre.GenreMapper
import com.example.movieproject.ui.mapper.populars.PopularsMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.http.Query
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searcUseCase: SearchMovieUseCase,
    private val genreUseCase: GenreUseCase,
    private val genreMapper: GenreMapper,
    private val popularsMapper: PopularsMapper,
    private val discoverUseCase: DiscoverUseCase
) : ViewModel() {

    private val _searchAdapterList = MutableLiveData<ArrayList<PopularUiModel>>()
    val searchAdapterList : LiveData<ArrayList<PopularUiModel>> = _searchAdapterList
    private val _genredapterList = MutableLiveData<ArrayList<GenreUiModel>>()
    val genreAdapterList : LiveData<ArrayList<GenreUiModel>> = _genredapterList

    private val _discoverAdapterList = MutableLiveData<ArrayList<PopularUiModel>>()
    val discoverAdapterList : LiveData<ArrayList<PopularUiModel>> = _discoverAdapterList

    val editTextValue = MutableLiveData<String>()

    fun discover(discoverId:String){
        discoverUseCase.discoverId(discoverId)
        discoverUseCase.execute(
            onSuccess = {
                popularsMapper.mapOnPopularsResponse(it)
                _discoverAdapterList.value = popularsMapper.popularsAdapterList
            },
            onError = {
                it.printStackTrace()
            }
        )
    }

    fun genreRequest(){
        genreUseCase.execute(
            onSuccess = {
                genreMapper.mapOnGenreResponse(it)
                _genredapterList.value=genreMapper.genreAdapterList
            },
            onError = {
                it.printStackTrace()
            }
        )
    }

    fun castRequest(query:String) {
        searcUseCase.getQuery(query)
        searcUseCase.execute(
            onSuccess = {
                popularsMapper.mapOnPopularsResponse(it)
                _searchAdapterList.value = popularsMapper.popularsAdapterList
            },
            onError = {
                it.printStackTrace()
            }
        )

    }
}