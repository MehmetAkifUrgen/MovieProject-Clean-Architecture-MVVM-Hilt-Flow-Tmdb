package com.example.movieproject.data.repository.cast

import com.example.movieproject.data.response.casting.CastResponse
import io.reactivex.rxjava3.core.Single

interface CastRepository {
     fun getCast(id: String): Single<CastResponse>
}
