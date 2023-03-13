package com.example.movieproject.ui.fragment.populars

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieproject.data.uimodel.populars.PopularUiModel
import com.example.movieproject.data.uimodel.upcoming.UpComingUiModel
import com.example.movieproject.ui.mapper.populars.PopularsMapper
import com.example.movieproject.data.usecase.populars.PopularsUseCase
import com.example.movieproject.data.usecase.upcoming.UpComingsUseCase
import com.example.movieproject.ui.mapper.upcoming.UpComingMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val popularsUseCase: PopularsUseCase,
    private val popularsMapper: PopularsMapper,
    private val upComingMapper:UpComingMapper,
    private val upComingsUseCase: UpComingsUseCase
) : ViewModel() {

    private val _agentAdapterList = MutableLiveData<ArrayList<PopularUiModel>>()
    val agentAdapterList : LiveData<ArrayList<PopularUiModel>> = _agentAdapterList

    private val _upcomingAdapterList = MutableLiveData<ArrayList<UpComingUiModel>>()
    val upcomingAdapterList : LiveData<ArrayList<UpComingUiModel>> = _upcomingAdapterList

    fun agentsRequest() {
       viewModelScope.launch {
           popularsUseCase.execute(
               onSuccess = {
                   popularsMapper.mapOnPopularsResponse(it)
                   _agentAdapterList.postValue(popularsMapper.popularsAdapterList)
               },
               onError = {
                   it.printStackTrace()
               }
           )
           upComingsUseCase.execute(
               onSuccess = {
                   upComingMapper.mapOnUpComingResponse(it)
                   _upcomingAdapterList.postValue(upComingMapper.upcomingsAdapterList)
               },
               onError = {
                   it.printStackTrace()
               }
           )
       }
    }
}