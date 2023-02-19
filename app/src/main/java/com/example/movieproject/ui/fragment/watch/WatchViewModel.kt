package com.example.movieproject.ui.fragment.watch

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieproject.data.uimodel.watch.WatchUiModel
import com.example.movieproject.data.usecase.watch.WatchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WatchViewModel @Inject constructor(
    private val watchUseCase: WatchUseCase,
) : ViewModel() {


    private val _pro =MutableLiveData<ArrayList<WatchUiModel>>()
    val pro: LiveData<ArrayList<WatchUiModel>> = _pro
    var array = arrayListOf<WatchUiModel>()



    fun watchRequest(id:String) {
        watchUseCase.id(id)
        watchUseCase.execute(
            onSuccess = { watchResponse ->
                Log.d("selam", watchResponse.results.toString())
                watchResponse.results.forEach { it1 ->
                    if(it1.link.indexOf("locale=AT") != -1){
                        Log.d("naber",it1.toString())
                      it1.flatrate.forEach {
                          array.add(it as WatchUiModel)
                      }
                        it1.buy.forEach {
                            array.add(it as WatchUiModel)
                        }
                  }
                }
                _pro.value=array
            },
            onError = {
                it.printStackTrace()
            }
        )
    }

}