package com.example.movieproject.data.usecase.watch

import com.example.movieproject.base.BaseUseCase
import com.example.movieproject.data.repository.popular.PopularRepository
import com.example.movieproject.data.response.popular.PopularDetailsResponse
import com.example.movieproject.data.response.popular.PopularResponse
import com.example.movieproject.data.response.watch.WatchResponse
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class WatchUseCase @Inject constructor(
    private val popularRepository: PopularRepository
) : BaseUseCase<WatchResponse>() {
    private lateinit var id : String

    fun id(uii: String) {
        id = uii
    }
    override fun buildUseCaseSingle(): Single<WatchResponse> {
        return popularRepository.getWatch(id)
    }
}