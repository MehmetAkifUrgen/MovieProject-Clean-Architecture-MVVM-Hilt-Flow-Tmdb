package com.example.movieproject.data.uimodel.cast

import com.example.movieproject.data.response.casting.Cast
import com.example.movieproject.data.response.casting.Crew

data class CastUiModel(
    var cast: List<Cast> ?= arrayListOf(),
    var crew: List<Crew>   ?= arrayListOf(),
    var id: Int ?= 0
)