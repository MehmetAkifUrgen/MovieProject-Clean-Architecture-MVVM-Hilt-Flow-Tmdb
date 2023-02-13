package com.example.movieproject.ui.mapper.populars


import com.example.movieproject.data.response.popular.PopularDetailsResponse
import com.example.movieproject.data.uimodel.populars.PopularDetailsUiModel
import javax.inject.Inject

open class PopularDetailMapper @Inject constructor() {

    var agentDetailItem: PopularDetailsUiModel? = null

    fun mapOnAgentDetailResponse(agentDetailResponse: PopularDetailsResponse) {
        addAgentDetailItem(agentDetailResponse)
    }

    private fun agentDetailResponseConvertToModel(agentDetailResponseItem: PopularDetailsResponse): PopularDetailsUiModel {
        return PopularDetailsUiModel().apply {
            agentDetailResponseItem.original_title.let { title -> this.original_title = title }
            agentDetailResponseItem.poster_path.let { poster_path -> this.poster_path = poster_path }
            agentDetailResponseItem.backdrop_path.let { backdrop_path -> this.backdrop_path = backdrop_path }
            agentDetailResponseItem.release_date.let { release_date -> this.release_date = release_date }
            agentDetailResponseItem.vote_average.let { vote_average -> this.vote_average = vote_average }
            agentDetailResponseItem.overview.let { overview -> this.overview = overview }
            agentDetailResponseItem.id.let { id -> this.id = id.toString() }
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