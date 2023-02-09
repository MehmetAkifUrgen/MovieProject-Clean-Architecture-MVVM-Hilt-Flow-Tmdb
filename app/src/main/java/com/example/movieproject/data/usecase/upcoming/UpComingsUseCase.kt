package com.example.movieproject.data.usecase.upcoming

import com.example.movieproject.base.BaseUseCase
import com.example.movieproject.data.repository.popular.PopularRepository
import com.example.movieproject.data.response.popular.PopularResponse
import com.example.movieproject.data.response.upcoming.UpComingResponse
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class UpComingsUseCase @Inject constructor(
    private val popularRepository: PopularRepository
) : BaseUseCase<UpComingResponse>() {

    override fun buildUseCaseSingle(): Single<UpComingResponse> {
        return popularRepository.getUpComing()
    }
}