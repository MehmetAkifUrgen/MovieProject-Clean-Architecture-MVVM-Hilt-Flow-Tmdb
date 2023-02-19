package com.example.movieproject.data.response.watch

data class WatchResultResponse(
    val flatrate:List<Flatrate>,
    val link:String,
    val buy:List<Buy>
)