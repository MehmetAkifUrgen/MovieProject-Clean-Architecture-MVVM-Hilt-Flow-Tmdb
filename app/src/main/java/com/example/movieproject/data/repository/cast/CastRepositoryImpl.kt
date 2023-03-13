package com.example.movieproject.data.repository.cast

import com.example.movieproject.data.api.ServiceInterface
import com.example.movieproject.data.response.casting.CastResponse
import kotlinx.coroutines.flow.Flow

class CastRepositoryImpl(private val serviceInterface: ServiceInterface) : CastRepository {
    override suspend fun getCast(id: String): Flow<CastResponse> {
        return serviceInterface.getCast(id)
    }
}