package com.example.movieproject.ui.fragment.watchList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieproject.data.uimodel.populars.PopularUiModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class WatchListViewModel: ViewModel() {
    private lateinit var auth: FirebaseAuth
    private val db = Firebase.firestore


    private val _watchlistAdapterList = MutableLiveData<ArrayList<PopularUiModel>>()
    val watchlistAdapterList : LiveData<ArrayList<PopularUiModel>> = _watchlistAdapterList



    fun getWatchList(){
        auth= Firebase.auth
        viewModelScope.launch {
            auth.currentUser?.let {
                db.collection(it.uid)
                    .get()
                    .addOnSuccessListener { result ->
                        val popularUiModels = ArrayList<PopularUiModel>()
                        for (document in result) {
                            val hashMap: MutableMap<String, Any> = document.data
                            val popularUiModel = PopularUiModel(
                                id = hashMap["id"] as? String ?: "",
                                title = hashMap["title"] as? String ?: "",
                                posterPath = hashMap["posterPath"] as? String ?: "",
                                // and so on for other properties
                            )
                            popularUiModels.add(popularUiModel)
                        }
                        _watchlistAdapterList.value = popularUiModels
                        Log.d("view",_watchlistAdapterList.value.toString())
                    }
                    .addOnFailureListener { exception ->
                        Log.w("TAG", "Error getting documents.", exception)
                    }
            }
        }
    }




}


