package com.example.movieproject.data.repository.search

import com.example.movieproject.data.api.ServiceInterface
import com.example.movieproject.data.response.genre.GenreResponse
import com.example.movieproject.data.response.popular.PopularResponse
import com.example.movieproject.data.response.popular.PopularDetailsResponse
import com.example.movieproject.data.response.upcoming.UpComingResponse
import com.example.movieproject.data.response.watch.WatchResponse
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow

class SearchRepositoryImpl(private val serviceInterface: ServiceInterface) : SearchRepository {


    override suspend fun searchMovie(query: String): Flow<PopularResponse> {
        return serviceInterface.searchMovie(query)
    }

    override suspend fun getGenre(): Flow<GenreResponse> {
        return serviceInterface.getGenres()
    }

    override suspend fun getDiscover(genre: String,page:Int): PopularResponse {
        return serviceInterface.getDiscover(genre,page)
    }

}