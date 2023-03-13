package com.example.movieproject.data.response.upcoming

import com.example.movieproject.data.response.popular.Movie

data class UpComingResponse(
    val dates: Dates,
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)