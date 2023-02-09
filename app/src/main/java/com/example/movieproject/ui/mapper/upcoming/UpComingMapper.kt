package com.example.movieproject.ui.mapper.upcoming

import com.example.movieproject.data.response.popular.PopularResponse
import com.example.movieproject.data.response.upcoming.UpComingResponse
import com.example.movieproject.data.uimodel.upcoming.UpComingUiModel
import javax.inject.Inject

open class UpComingMapper @Inject constructor() {

    var upcomingsAdapterList = ArrayList<UpComingUiModel>()

    fun mapOnUpComingResponse(agentsResponse: UpComingResponse){
        upcomingsAdapterList.clear()
        addAgentsItem(agentsResponse)
    }

    private fun agentsResponseConvertToModel(agentsResponseItem: com.example.movieproject.data.response.upcoming.Result): UpComingUiModel {
        return UpComingUiModel().apply {
            agentsResponseItem.title.let { title-> this.title = title }
            agentsResponseItem.poster_path.let { image-> this.posterPath = image }
            agentsResponseItem.id.let { id-> this.id = id.toString() }
        }
    }

    private fun addAgentsItem(agentsResponse: UpComingResponse) {
        agentsResponse.results.forEach { response ->
            val agentsItem = agentsResponseConvertToModel(response)
            upcomingsAdapterList.add(agentsItem)
        }
    }
}