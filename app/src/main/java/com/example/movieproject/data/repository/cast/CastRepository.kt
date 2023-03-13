package com.example.movieproject.data.repository.cast

import com.example.movieproject.data.response.casting.CastResponse
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow

interface CastRepository {
     suspend fun getCast(id: String): Flow<CastResponse>
}
