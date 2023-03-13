package com.example.movieproject.ui.fragment.populars

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieproject.data.uimodel.populars.PopularDetailsUiModel
import com.example.movieproject.data.usecase.populars.PopularDetailUseCase
import com.example.movieproject.ui.mapper.populars.PopularDetailMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val agentDetailUseCase: PopularDetailUseCase,
    private val agentDetailMapper: PopularDetailMapper
) : ViewModel() {

    private val _agentDetailItem = MutableLiveData<PopularDetailsUiModel>()
    val agentDetailItem : LiveData<PopularDetailsUiModel> = _agentDetailItem

    fun agentDetailRequest(id:String) {
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