package com.example.movieproject.data.repository.cast

import com.example.movieproject.data.api.ServiceInterface
import com.example.movieproject.data.response.casting.CastResponse
import io.reactivex.rxjava3.core.Single

class CastRepositoryImpl(private val serviceInterface: ServiceInterface) : CastRepository {
    override fun getCast(id: String): Single<CastResponse> {
        return serviceInterface.getCast(id)
    }
}