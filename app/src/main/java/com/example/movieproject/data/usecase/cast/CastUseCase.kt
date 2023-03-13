package com.example.movieproject.data.usecase.cast

import com.example.movieproject.base.BaseUseCase
import com.example.movieproject.data.repository.cast.CastRepository
import com.example.movieproject.data.repository.popular.PopularRepository
import com.example.movieproject.data.response.casting.CastResponse
import com.example.movieproject.data.response.popular.PopularDetailsResponse
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CastUseCase @Inject constructor(
    private val castRepository: CastRepository
) : BaseUseCase<CastResponse>() {

    private lateinit var id : String

    fun popularId(uii: String) {
        id = uii
    }

    override suspend fun buildUseCaseFlow(): Flow<CastResponse> {
        return castRepository.getCast(id)
    }

}