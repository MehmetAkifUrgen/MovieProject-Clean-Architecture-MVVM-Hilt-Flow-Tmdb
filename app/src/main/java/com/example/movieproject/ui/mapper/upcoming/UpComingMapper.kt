package com.example.movieproject.ui.mapper.upcoming

import com.example.movieproject.data.response.upcoming.UpComingResponse
import com.example.movieproject.data.uimodel.upcoming.UpComingUiModel
import javax.inject.Inject

open class UpComingMapper @Inject constructor() {

    var upcomingsAdapterList = ArrayList<UpComingUiModel>()

    fun mapOnUpComingResponse(agentsResponse: UpComingResponse){
        upcomingsAdapterList.clear()
        addUpcomingItem(agentsResponse)
    }

    private fun upcomingsResponseConvertToModel(popularsResponseItem: com.example.movieproject.data.response.upcoming.Result): UpComingUiModel {
        return UpComingUiModel().apply {
            popularsResponseItem.title.let { title-> this.title = title }
            popularsResponseItem.poster_path.let { image-> this.posterPath = image }
            popularsResponseItem.id.let { id-> this.id = id.toString() }
        }
    }

    private fun addUpcomingItem(upcomingsResponse: UpComingResponse) {
        upcomingsResponse.results.forEach { response ->
            val agentsItem = upcomingsResponseConvertToModel(response)
            upcomingsAdapterList.add(agentsItem)
        }
    }
}