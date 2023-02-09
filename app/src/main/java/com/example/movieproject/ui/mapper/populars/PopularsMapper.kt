package com.example.movieproject.ui.mapper.populars

import com.example.movieproject.data.response.popular.PopularResponse
import javax.inject.Inject

open class PopularsMapper @Inject constructor() {

    var agentsAdapterList = ArrayList<com.example.movieproject.data.uimodel.populars.Popular>()

    fun mapOnAgentsResponse(agentsResponse: PopularResponse){
        agentsAdapterList.clear()
        addAgentsItem(agentsResponse)
    }

    private fun agentsResponseConvertToModel(agentsResponseItem: com.example.movieproject.data.response.popular.Result): com.example.movieproject.data.uimodel.populars.Popular {
        return com.example.movieproject.data.uimodel.populars.Popular().apply {
            agentsResponseItem.title.let { title-> this.title = title }
            agentsResponseItem.poster_path.let { image-> this.posterPath = image }
            agentsResponseItem.id.let { id-> this.id = id.toString() }
        }
    }

    private fun addAgentsItem(agentsResponse: PopularResponse) {
        agentsResponse.results.forEach { response ->
            val agentsItem = agentsResponseConvertToModel(response)
            agentsAdapterList.add(agentsItem)
        }
    }
}