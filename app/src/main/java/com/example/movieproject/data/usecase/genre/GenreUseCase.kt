package com.example.movieproject.data.usecase.genre

import com.example.movieproject.base.BaseUseCase
import com.example.movieproject.data.repository.popular.PopularRepository
import com.example.movieproject.data.repository.search.SearchRepository
import com.example.movieproject.data.response.genre.GenreResponse
import com.example.movieproject.data.response.popular.PopularResponse
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GenreUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) : BaseUseCase<GenreResponse>() {

    override fun buildUseCaseSingle(): Single<GenreResponse> {
        return searchRepository.getGenre()
    }
}