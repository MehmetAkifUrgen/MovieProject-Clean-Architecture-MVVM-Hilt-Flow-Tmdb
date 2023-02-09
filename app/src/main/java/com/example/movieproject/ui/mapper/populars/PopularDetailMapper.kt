package com.example.movieproject.ui.mapper.populars


import com.example.movieproject.data.response.popular.PopularDetailsResponse
import com.example.movieproject.data.uimodel.populars.PopularDetails
import javax.inject.Inject

open class PopularDetailMapper @Inject constructor() {

    var agentDetailItem: PopularDetails? = null

    fun mapOnAgentDetailResponse(agentDetailResponse: PopularDetailsResponse) {
        addAgentDetailItem(agentDetailResponse)
    }

    private fun agentDetailResponseConvertToModel(agentDetailResponseItem: PopularDetailsResponse): PopularDetails {
        return PopularDetails().apply {
            agentDetailResponseItem.original_title.let { description -> this.original_title = description }
            agentDetailResponseItem.poster_path.let { displayName -> this.poster_path = displayName }
            agentDetailResponseItem.id?.let { id -> this.id = id.toString() }
          //  abilities = abilityListConvertToModel(agentDetailResponseItem)
        }
    }

    private fun addAgentDetailItem(agentDetailResponse: PopularDetailsResponse) {
        agentDetailResponse.let { response ->
            agentDetailItem = agentDetailResponseConvertToModel(response)
        }
    }

   /* private fun abilityListConvertToModel(agentDetailResponseItem: AgentsResponseItem): ArrayList<AbilityItem> {
        val abilityList = arrayListOf<AbilityItem>()
        agentDetailResponseItem.abilities?.forEach { ability ->
            abilityList.add(
                AbilityItem(
                    ability.description,
                    ability.displayIcon,
                    ability.displayName,
                )
            )
        }
        return abilityList
    }*/
}