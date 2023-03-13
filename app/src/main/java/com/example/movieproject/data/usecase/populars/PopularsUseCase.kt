package com.example.movieproject.data.usecase.populars

import com.example.movieproject.base.BaseUseCase
import com.example.movieproject.data.repository.popular.PopularRepository
import com.example.movieproject.data.response.popular.PopularResponse
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PopularsUseCase @Inject constructor(
    private val popularRepository: PopularRepository
) : BaseUseCase<PopularResponse>() {

    var page:Int = 1

    fun pages(it:Int){
        page=it
    }

    override suspend fun buildUseCaseFlow(): Flow<PopularResponse> {
        return popularRepository.getPopulars(page)
    }
}