package com.example.movieproject.data.repository.search

import com.example.movieproject.data.response.genre.GenreResponse
import com.example.movieproject.data.response.popular.PopularResponse
import com.example.movieproject.data.response.popular.PopularDetailsResponse
import com.example.movieproject.data.response.upcoming.UpComingResponse
import com.example.movieproject.data.response.watch.WatchResponse
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
     suspend fun searchMovie(query:String): Flow<PopularResponse>
     suspend fun getGenre():Flow<GenreResponse>
     suspend  fun getDiscover(genre:String,page:Int,startDate:String,endDate:String):PopularResponse
}
