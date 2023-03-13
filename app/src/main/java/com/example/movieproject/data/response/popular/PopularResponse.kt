package com.example.movieproject.data.response.popular



data class PopularResponse(
    val page: Int?,
    val total_results: Int?,
    val total_pages: Int?,
    val results: List<Movie>
)
