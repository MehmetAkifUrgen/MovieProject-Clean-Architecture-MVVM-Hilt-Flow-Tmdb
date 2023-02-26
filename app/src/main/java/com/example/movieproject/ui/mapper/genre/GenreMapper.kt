package com.example.movieproject.ui.mapper.genre

import com.example.movieproject.data.response.genre.GenreResponse
import com.example.movieproject.data.response.genre.GenreXResponse
import com.example.movieproject.data.response.popular.PopularResponse
import com.example.movieproject.data.uimodel.genre.GenreUiModel
import javax.inject.Inject

open class GenreMapper @Inject constructor() {

    var genreAdapterList = ArrayList<GenreUiModel>()

    fun mapOnGenreResponse(genreResponse: GenreResponse){
        genreAdapterList.clear()
        addGenreItem(genreResponse)
    }

    private fun genreResponseConvertToModel(genreResponseItem: GenreXResponse): GenreUiModel {
        return GenreUiModel().apply {
            genreResponseItem.name.let { name-> this.name = name }
            genreResponseItem.id.let { id-> this.id = id.toString() }
        }
    }

    private fun addGenreItem(genreResponse: GenreResponse) {
        genreResponse.genres.forEach { response ->
            val genreItem = genreResponseConvertToModel(response)
            genreAdapterList.add(genreItem)
        }
    }
}