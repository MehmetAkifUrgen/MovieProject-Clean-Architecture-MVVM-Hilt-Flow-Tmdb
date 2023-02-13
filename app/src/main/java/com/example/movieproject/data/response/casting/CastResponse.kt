package com.example.movieproject.data.response.casting

data class CastResponse(
    val cast: List<Cast>,
    val crew: List<Crew>,
    val id: Int
)