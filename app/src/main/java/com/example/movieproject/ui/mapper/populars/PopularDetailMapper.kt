package com.example.movieproject.ui.mapper.populars


import com.example.movieproject.data.response.popular.PopularDetailsResponse
import com.example.movieproject.data.uimodel.populars.PopularDetailsUiModel
import javax.inject.Inject

open class PopularDetailMapper @Inject constructor() {

    var popularDetailItem: PopularDetailsUiModel? = null

    fun mapOnPopularDetailResponse(popularDetailResponse: PopularDetailsResponse) {
        addPopularDetailItem(popularDetailResponse)
    }

    private fun popularDetailResponseConvertToModel(popularDetailResponseItem: PopularDetailsResponse): PopularDetailsUiModel {
        return PopularDetailsUiModel().apply {
            popularDetailResponseItem.original_title.let { title -> this.original_title = title }
            popularDetailResponseItem.poster_path.let { poster_path -> this.poster_path = poster_path }
            popularDetailResponseItem.backdrop_path.let { backdrop_path -> this.backdrop_path = backdrop_path }
            popularDetailResponseItem.release_date.let { release_date -> this.release_date = release_date }
            popularDetailResponseItem.vote_average.let { vote_average -> this.vote_average = vote_average }
            popularDetailResponseItem.overview.let { overview -> this.overview = overview }
            popularDetailResponseItem.id.let { id -> this.id = id.toString() }
        }
    }

    private fun addPopularDetailItem(popularDetailResponse: PopularDetailsResponse) {
        popularDetailResponse.let { response ->
            popularDetailItem = popularDetailResponseConvertToModel(response)
        }
    }

}