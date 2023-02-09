package com.example.movieproject.data.uimodel.populars

import com.example.movieproject.data.response.popular.Result


data class Popular(
    var title:String ?= null,
    var posterPath:String ?= null,
    var id:String ?= null
)