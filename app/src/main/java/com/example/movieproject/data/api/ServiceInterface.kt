package com.example.movieproject.data.api

import com.example.movieproject.data.response.popular.PopularResponse
import com.example.movieproject.data.response.popular.PopularDetailsResponse
import com.example.movieproject.data.response.upcoming.UpComingResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ServiceInterface {

    @GET("movie/popular?api_key=b953ac9f9bd22f92fd0cc94a9cc906b1&language=en-US&page=1")
    fun getPopulars(): Single<PopularResponse>

    @GET("movie/upcoming?api_key=b953ac9f9bd22f92fd0cc94a9cc906b1&language=en-US&page=1")
    fun getUpComing(): Single<UpComingResponse>

    @GET("movie/{id}?api_key=b953ac9f9bd22f92fd0cc94a9cc906b1&language=en-US")
    fun getPopularDetails(@Path("id") id: String): Single<PopularDetailsResponse>

   /* @GET("maps")
    fun getMaps(): Single<MapsResponse>

    @GET("maps/{mapUuid}")
    fun getMapDetail(@Path("mapUuid") mapUuid: String): Single<MapDetailResponse>

    @GET("weapons")
    fun getWeapons(): Single<WeaponsResponse>

    @GET("weapons/{weaponUuid}")
    fun getWeaponDetail(@Path("weaponUuid") weaponUuid: String): Single<WeaponDetailResponse>

    @GET("competitivetiers")
    fun getTiers(): Single<TiersResponse>*/

}