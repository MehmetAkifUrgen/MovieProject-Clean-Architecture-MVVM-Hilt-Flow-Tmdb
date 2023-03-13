package com.example.movieproject.data.usecase.genre

import com.example.movieproject.base.BaseUseCase
import com.example.movieproject.data.repository.popular.PopularRepository
import com.example.movieproject.data.repository.search.SearchRepository
import com.example.movieproject.data.response.genre.GenreResponse
import com.example.movieproject.data.response.popular.PopularResponse
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GenreUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) : BaseUseCase<GenreResponse>() {
    override suspend fun buildUseCaseFlow(): Flow<GenreResponse> {
        return searchRepository.getGenre()
    }


}