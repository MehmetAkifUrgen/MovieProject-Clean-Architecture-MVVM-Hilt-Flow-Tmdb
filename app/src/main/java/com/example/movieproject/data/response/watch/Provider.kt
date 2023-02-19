package com.example.movieproject.data.response.watch

data class Provider(
    val buy: List<Buy>,
    val flatrate: List<Flatrate>,
    val link: String
)