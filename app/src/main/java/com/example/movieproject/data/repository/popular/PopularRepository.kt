package com.example.movieproject.data.repository.popular

import com.example.movieproject.data.response.genre.GenreResponse
import com.example.movieproject.data.response.popular.PopularResponse
import com.example.movieproject.data.response.popular.PopularDetailsResponse
import com.example.movieproject.data.response.upcoming.UpComingResponse
import com.example.movieproject.data.response.watch.WatchResponse
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow

interface PopularRepository {

    suspend fun getPopulars(page:Int): PopularResponse
    suspend fun getUpComing(page: Int): UpComingResponse
    suspend fun getWatch(id: String):Flow<WatchResponse>


    suspend fun getPopularDetails(id: String): Flow<PopularDetailsResponse>
}
