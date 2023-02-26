package com.example.movieproject.data.repository.search

import com.example.movieproject.data.api.ServiceInterface
import com.example.movieproject.data.response.genre.GenreResponse
import com.example.movieproject.data.response.popular.PopularResponse
import com.example.movieproject.data.response.popular.PopularDetailsResponse
import com.example.movieproject.data.response.upcoming.UpComingResponse
import com.example.movieproject.data.response.watch.WatchResponse
import io.reactivex.rxjava3.core.Single

class SearchRepositoryImpl(private val serviceInterface: ServiceInterface) : SearchRepository {


    override fun searchMovie(query: String): Single<PopularResponse> {
        return serviceInterface.searchMovie(query)
    }

    override fun getGenre(): Single<GenreResponse> {
        return serviceInterface.getGenres()
    }

    override fun getDiscover(genre: String): Single<PopularResponse> {
        return serviceInterface.getDiscover(genre)
    }

}