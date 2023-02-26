package com.example.movieproject.data.repository.popular

import com.example.movieproject.data.api.ServiceInterface
import com.example.movieproject.data.response.genre.GenreResponse
import com.example.movieproject.data.response.popular.PopularResponse
import com.example.movieproject.data.response.popular.PopularDetailsResponse
import com.example.movieproject.data.response.upcoming.UpComingResponse
import com.example.movieproject.data.response.watch.WatchResponse
import io.reactivex.rxjava3.core.Single

class PopularRepositoryImpl(private val serviceInterface: ServiceInterface) : PopularRepository {

    override fun getPopulars(): Single<PopularResponse> {
        return serviceInterface.getPopulars()
    }

    override fun getUpComing(): Single<UpComingResponse> {
        return serviceInterface.getUpComing()
    }

    override fun getWatch(id: String): Single<WatchResponse> {
        return serviceInterface.getWatch(id)
    }

    override fun getPopularDetails(id: String): Single<PopularDetailsResponse> {
        return serviceInterface.getPopularDetails(id)
    }
}