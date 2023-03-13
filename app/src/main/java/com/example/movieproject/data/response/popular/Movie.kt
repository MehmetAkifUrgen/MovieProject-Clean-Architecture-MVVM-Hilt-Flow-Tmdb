package com.example.movieproject.data.response.popular

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


data class Movie(
    val id: Int?,
    val title: String?,
    val poster_path: String?,
    val release_date: String?,
    val vote_average: Float?
)
