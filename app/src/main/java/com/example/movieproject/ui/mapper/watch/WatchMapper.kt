package com.example.movieproject.ui.mapper.watch

import android.util.Log
import com.example.movieproject.data.response.casting.Cast
import com.example.movieproject.data.response.casting.CastResponse
import com.example.movieproject.data.response.casting.Crew
import com.example.movieproject.data.response.popular.PopularDetailsResponse
import com.example.movieproject.data.response.popular.PopularResponse
import com.example.movieproject.data.response.watch.WatchResponse
import com.example.movieproject.data.response.watch.WatchResultResponse
import com.example.movieproject.data.uimodel.cast.CastDetailUiModel
import com.example.movieproject.data.uimodel.cast.CastUiModel
import com.example.movieproject.data.uimodel.cast.CrewDetailUiModel
import com.example.movieproject.data.uimodel.populars.PopularDetailsUiModel
import com.example.movieproject.data.uimodel.watch.WatchUiModel
import javax.inject.Inject

open class WatchMapper @Inject constructor() {

     var watchAdapterList = ArrayList<WatchUiModel>()


//    fun mapOnWatchResponse(watchResponse: WatchResponse){
//        watchAdapterList.clear()
//        addWatchItem(watchResponse)
//        foundTR()
//    }
//
//    private fun watchResponseConvertToModel(watchResponseItem: WatchResultResponse): WatchUiModel {
//        return WatchUiModel().apply {
//            watchResponseItem.logo_path.let { logo_path-> this.logo_path = logo_path }
//            watchResponseItem.provider_name.let { provider_name-> this.provider_name = provider_name }
//        }
//    }
//
//
//
//    private fun addWatchItem(watchResponse: WatchResponse) {
//        watchResponse.results.forEach { response ->
//            val watchItem = watchResponseConvertToModel(response)
//            watchAdapterList.add(watchItem)
//        }
//    }
//    private fun foundTR(){
//      Log.e("provider",watchAdapterList.toString())
//        watchAdapterList.map {
//            it
//        }
////        getJSONObject(i)["betsData"] as JSONArray
////
////        val checkedState = remember { mutableStateOf(false) }
////        val id = dat2a.getJSONObject(i).getString("id")
////        Log.i("ID: ", id)
////
////        //var active = jsonArray.getJSONObject(i).getBoolean("active")
////        //Log.i("Active: ", active.toString())
////        // Employee Name
////        val bet = dat2a.getJSONObject(i).getString("bet")
////        Log.i("Employee Name: ", bet)
////
////        // Employee Salary
////        val odd = dat2a.getJSONObject(i).getString("odd")
////        Log.i("Employee Salary: ", odd)
//    }

}