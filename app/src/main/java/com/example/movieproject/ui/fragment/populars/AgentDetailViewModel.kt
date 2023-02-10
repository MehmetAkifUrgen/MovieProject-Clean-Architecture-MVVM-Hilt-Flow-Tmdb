package com.example.movieproject.ui.fragment.populars

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieproject.data.uimodel.populars.PopularDetails
import com.example.movieproject.data.usecase.populars.PopularDetailUseCase
import com.example.movieproject.ui.mapper.populars.PopularDetailMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AgentDetailViewModel @Inject constructor(
    private val agentDetailUseCase: PopularDetailUseCase,
    private val agentDetailMapper: PopularDetailMapper
) : ViewModel() {

    private val _agentDetailItem = MutableLiveData<PopularDetails>()
    val agentDetailItem : LiveData<PopularDetails> = _agentDetailItem

    fun agentDetailRequest(id:String) {
        agentDetailUseCase.popularId(id)
        agentDetailUseCase.execute(
            onSuccess = {
                agentDetailMapper.mapOnAgentDetailResponse(it)
                _agentDetailItem.value = agentDetailMapper.agentDetailItem
            },
            onError = {
                it.printStackTrace()
            }
        )
    }
}