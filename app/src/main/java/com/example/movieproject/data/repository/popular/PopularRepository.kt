package com.example.movieproject.data.repository.popular

import com.example.movieproject.data.response.popular.PopularResponse
import com.example.movieproject.data.response.popular.PopularDetailsResponse
import com.example.movieproject.data.response.upcoming.UpComingResponse
import com.example.movieproject.data.response.watch.WatchResponse
import io.reactivex.rxjava3.core.Single

interface PopularRepository {

     fun getPopulars(): Single<PopularResponse>
     fun getUpComing(): Single<UpComingResponse>
     fun getWatch(id: String):Single<WatchResponse>
     fun searchMovie(query:String):Single<PopularResponse>

     fun getPopularDetails(id: String): Single<PopularDetailsResponse>
}
