package com.example.movieproject.data.usecase.populars

import com.example.movieproject.base.BaseUseCase
import com.example.movieproject.data.repository.popular.PopularRepository
import com.example.movieproject.data.response.popular.PopularDetailsResponse
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PopularDetailUseCase @Inject constructor(
    private val popularRepository: PopularRepository
) : BaseUseCase<PopularDetailsResponse>() {

    private lateinit var id : String

    fun popularId(uii: String) {
        id = uii
    }

    override suspend fun buildUseCaseFlow(): Flow<PopularDetailsResponse> {
        return popularRepository.getPopularDetails(id)
    }
}