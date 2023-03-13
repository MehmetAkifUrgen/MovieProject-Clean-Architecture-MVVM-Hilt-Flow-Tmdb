package com.example.movieproject.ui.fragment.cast

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieproject.data.uimodel.cast.CastDetailUiModel
import com.example.movieproject.data.uimodel.cast.CrewDetailUiModel
import com.example.movieproject.data.usecase.cast.CastUseCase
import com.example.movieproject.ui.mapper.cast.CastMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CastViewModel @Inject constructor(
    private val castUseCase: CastUseCase,
    private val castMapper: CastMapper,
) : ViewModel() {

    private val _castAdapterList = MutableLiveData<ArrayList<CastDetailUiModel>>()
    private val _director = MutableLiveData<CrewDetailUiModel>()
    private val _crewAdapterList = MutableLiveData<ArrayList<CrewDetailUiModel>>()
    val castAdapterList : LiveData<ArrayList<CastDetailUiModel>> = _castAdapterList
    val crewAdapterList : LiveData<ArrayList<CrewDetailUiModel>> = _crewAdapterList
    val director : LiveData<CrewDetailUiModel> = _director


    fun castRequest(id:String) {
       viewModelScope.launch {
           castUseCase.popularId(id)
           castUseCase.execute(
               onSuccess = {
                   castMapper.mapOnCastResponse(it)
                   _castAdapterList.postValue(castMapper.castAdapterList)
                   castMapper.mapOnCrewResponse(it)
                   _director.postValue(castMapper.director)
                   _crewAdapterList.postValue(castMapper.crewData)
               },
               onError = {
                   it.printStackTrace()
               }
           )
       }
    }
}