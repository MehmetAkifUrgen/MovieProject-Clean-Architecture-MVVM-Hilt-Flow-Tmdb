package com.example.movieproject.ui.mapper.populars

import com.example.movieproject.data.response.popular.PopularResponse
import javax.inject.Inject

open class PopularsMapper @Inject constructor() {

    var popularsAdapterList = ArrayList<com.example.movieproject.data.uimodel.populars.PopularUiModel>()

    fun mapOnPopularsResponse(agentsResponse: PopularResponse){
        popularsAdapterList.clear()
        addPopularsItem(agentsResponse)
    }

    private fun popularsResponseConvertToModel(agentsResponseItem: com.example.movieproject.data.response.popular.Result): com.example.movieproject.data.uimodel.populars.PopularUiModel {
        return com.example.movieproject.data.uimodel.populars.PopularUiModel().apply {
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