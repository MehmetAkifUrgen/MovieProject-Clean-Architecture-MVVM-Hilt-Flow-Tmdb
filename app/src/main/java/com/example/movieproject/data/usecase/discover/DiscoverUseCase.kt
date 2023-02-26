package com.example.movieproject.data.usecase.discover

import com.example.movieproject.base.BaseUseCase
import com.example.movieproject.data.repository.cast.CastRepository
import com.example.movieproject.data.repository.popular.PopularRepository
import com.example.movieproject.data.repository.search.SearchRepository
import com.example.movieproject.data.response.casting.CastResponse
import com.example.movieproject.data.response.popular.PopularDetailsResponse
import com.example.movieproject.data.response.popular.PopularResponse
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class DiscoverUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) : BaseUseCase<PopularResponse>() {

    private lateinit var discover : String

    fun discoverId(uii: String) {
        discover = uii
    }
    override fun buildUseCaseSingle(): Single<PopularResponse> {
        return searchRepository.getDiscover(discover)
    }
}