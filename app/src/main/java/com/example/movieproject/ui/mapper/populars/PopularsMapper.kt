package com.example.movieproject.ui.mapper.populars

import com.example.movieproject.data.response.popular.Movie
import com.example.movieproject.data.response.popular.PopularResponse
import com.example.movieproject.data.uimodel.populars.PopularUiModel
import javax.inject.Inject

open class PopularsMapper @Inject constructor() {

    var popularsAdapterList = ArrayList<PopularUiModel>()

    fun mapOnPopularsResponse(agentsResponse: PopularResponse){
        popularsAdapterList.clear()
        addPopularsItem(agentsResponse)
    }

    private fun popularsResponseConvertToModel(agentsResponseItem: Movie): PopularUiModel {
        return PopularUiModel().apply {
            agentsResponseItem.title.let { title-> this.title = title }
            agentsResponseItem.poster_path.let { image-> this.posterPath = image }
            agentsResponseItem.id.let { id-> this.id = id.toString() }
        }
    }

    private fun addPopularsItem(agentsResponse: PopularResponse) {
        agentsResponse.results.forEach { response ->
            val agentsItem = popularsResponseConvertToModel(response)
            popularsAdapterList.add(agentsItem)
        }
    }
}