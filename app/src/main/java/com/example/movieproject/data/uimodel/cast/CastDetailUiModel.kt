package com.example.movieproject.data.uimodel.cast

data class CastDetailUiModel(
    var adult: Boolean ?=false,
    var cast_id: Int ?= 0,
    var character: String ?= "",
    var credit_id: String ?= "",
    var gender: Int ?= 0,
    var id: Int ?= 0,
    var known_for_department: String ?= "",
    var name: String ?= "",
    var order: Int ?= 0,
    var original_name: String ?= "",
    var popularity: Double ?= 0.0,
    var profile_path: String ?= ""
)