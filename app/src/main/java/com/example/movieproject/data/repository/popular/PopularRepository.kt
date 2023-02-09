package com.example.movieproject.data.repository.popular

import com.example.movieproject.data.response.popular.PopularResponse
import com.example.movieproject.data.response.popular.PopularDetailsResponse
import com.example.movieproject.data.response.upcoming.UpComingResponse
import io.reactivex.rxjava3.core.Single

interface PopularRepository {

     fun getPopulars(): Single<PopularResponse>
     fun getUpComing(): Single<UpComingResponse>

     fun getPopularDetails(id: String): Single<PopularDetailsResponse>
}
