package com.example.movieproject.data.uimodel.populars

import com.example.movieproject.data.response.popular.*

data class PopularDetails (
    var adult: Boolean  ?= false,
    var backdrop_path: String  ?= "",
    var budget: Int ?=0,
    var genres: List<Genre> ?= arrayListOf(),
    var homepage: String  ?= "",
    var id: String = "",
    var imdb_id: String  ?= "",
    var original_language: String  ?= "" ,
    var original_title: String  ?= "" ,
    var overview: String  ?= "",
    var poster_path: String  ?= "",
    var production_companies: List<ProductionCompany> ?= arrayListOf(),
    var production_countries: List<ProductionCountry> ?= arrayListOf(),
    var release_date: String  ?= "",

    var spoken_languages: List<SpokenLanguage> ?= arrayListOf(),
    var status: String  ?= "",
    var tagline: String  ?= "",
    var title: String  ?= "",

)

