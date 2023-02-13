package com.example.movieproject.ui.fragment.cast

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieproject.data.uimodel.cast.CastDetailUiModel
import com.example.movieproject.data.uimodel.populars.PopularUiModel
import com.example.movieproject.data.uimodel.upcoming.UpComingUiModel
import com.example.movieproject.data.usecase.cast.CastUseCase
import com.example.movieproject.ui.mapper.populars.PopularsMapper
import com.example.movieproject.data.usecase.populars.PopularsUseCase
import com.example.movieproject.data.usecase.upcoming.UpComingsUseCase
import com.example.movieproject.ui.mapper.cast.CastMapper
import com.example.movieproject.ui.mapper.upcoming.UpComingMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CastViewModel @Inject constructor(
    private val castUseCase: CastUseCase,
    private val castMapper: CastMapper,
) : ViewModel() {

    private val _castAdapterList = MutableLiveData<ArrayList<CastDetailUiModel>>()
    val castAdapterList : LiveData<ArrayList<CastDetailUiModel>> = _castAdapterList

    fun castRequest(id:String) {
        castUseCase.popularId(id)
        castUseCase.execute(
            onSuccess = {
                castMapper.mapOnCastResponse(it)
                _castAdapterList.value = castMapper.castAdapterList

            },
            onError = {
                it.printStackTrace()
            }
        )
    }
}