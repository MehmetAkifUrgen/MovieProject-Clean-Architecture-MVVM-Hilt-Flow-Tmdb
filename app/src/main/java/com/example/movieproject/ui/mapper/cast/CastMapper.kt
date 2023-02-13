package com.example.movieproject.ui.mapper.cast

import com.example.movieproject.data.response.casting.Cast
import com.example.movieproject.data.response.casting.CastResponse
import com.example.movieproject.data.response.popular.PopularResponse
import com.example.movieproject.data.uimodel.cast.CastDetailUiModel
import com.example.movieproject.data.uimodel.cast.CastUiModel
import javax.inject.Inject

open class CastMapper @Inject constructor() {

    var castAdapterList = ArrayList<CastDetailUiModel>()

    fun mapOnCastResponse(castResponse: CastResponse){
        castAdapterList.clear()
        addCastItem(castResponse)
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

    private fun addCastItem(castResponse: CastResponse) {
        castResponse.cast.forEach { response ->
            val castItem = castResponseConvertToModel(response)
            castAdapterList.add(castItem)
        }
    }
}