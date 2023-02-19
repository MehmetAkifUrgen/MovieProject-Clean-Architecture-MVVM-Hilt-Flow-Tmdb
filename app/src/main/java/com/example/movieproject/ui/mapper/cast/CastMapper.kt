package com.example.movieproject.ui.mapper.cast

import com.example.movieproject.data.response.casting.Cast
import com.example.movieproject.data.response.casting.CastResponse
import com.example.movieproject.data.response.casting.Crew
import com.example.movieproject.data.response.popular.PopularDetailsResponse
import com.example.movieproject.data.response.popular.PopularResponse
import com.example.movieproject.data.uimodel.cast.CastDetailUiModel
import com.example.movieproject.data.uimodel.cast.CastUiModel
import com.example.movieproject.data.uimodel.cast.CrewDetailUiModel
import com.example.movieproject.data.uimodel.populars.PopularDetailsUiModel
import javax.inject.Inject

open class CastMapper @Inject constructor() {

    var castAdapterList = ArrayList<CastDetailUiModel>()
    var crewData = ArrayList<CrewDetailUiModel>()
    var director : CrewDetailUiModel? = null


    fun mapOnCastResponse(castResponse: CastResponse){
        castAdapterList.clear()
        addCastItem(castResponse)
    }fun mapOnCrewResponse(crewResponse: CastResponse){
        crewData.clear()
        addCrewItem(crewResponse)
        foundDirector()
    }

    private fun castResponseConvertToModel(castResponseItem: Cast): CastDetailUiModel {
        return CastDetailUiModel().apply {
            castResponseItem.profile_path.let { image-> this.profile_path = image }
            castResponseItem.character.let { character-> this.character = character }
            castResponseItem.original_name.let { ori_name-> this.original_name =  ori_name}
            castResponseItem.id.let { id-> this.id =  id}
            castResponseItem.known_for_department.let { known_for_department-> this.known_for_department =  known_for_department}

        }
    }
    private fun crewResponseConvertToModel(crewResponseItem: Crew): CrewDetailUiModel {
        return CrewDetailUiModel().apply {
            crewResponseItem.profile_path.let { image-> this.profile_path = image }
            crewResponseItem.original_name.let { ori_name-> this.name =  ori_name}
            crewResponseItem.id.let { id-> this.id =  id}
            crewResponseItem.known_for_department.let { known_for_department-> this.known_for_department =  known_for_department}
            crewResponseItem.job.let { job -> this.job = job }
            crewResponseItem.credit_id.let { credit_id -> this.credit_id = credit_id }
        }
    }


    private fun addCastItem(castResponse: CastResponse) {
        castResponse.cast.forEach { response ->
            val castItem = castResponseConvertToModel(response)
            castAdapterList.add(castItem)
        }
    }
    private fun foundDirector(){
        crewData.map {
            if (it.job == "Director"){
                director=it
            }
        }
    }
    private fun addCrewItem(crewResponse: CastResponse) {
        crewResponse.crew.forEach { response ->
            val crewItem = crewResponseConvertToModel(response)
            crewData.add(crewItem)
        }
    }
}