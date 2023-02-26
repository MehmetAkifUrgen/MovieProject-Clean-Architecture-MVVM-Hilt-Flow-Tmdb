package com.example.movieproject.data.repository.search

import com.example.movieproject.data.response.genre.GenreResponse
import com.example.movieproject.data.response.popular.PopularResponse
import com.example.movieproject.data.response.popular.PopularDetailsResponse
import com.example.movieproject.data.response.upcoming.UpComingResponse
import com.example.movieproject.data.response.watch.WatchResponse
import io.reactivex.rxjava3.core.Single

interface SearchRepository {
     fun searchMovie(query:String):Single<PopularResponse>
     fun getGenre():Single<GenreResponse>
     fun getDiscover(genre:String):Single<PopularResponse>
}
