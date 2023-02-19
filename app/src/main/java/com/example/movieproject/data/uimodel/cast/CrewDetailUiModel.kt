package com.example.movieproject.data.uimodel.cast

data class CrewDetailUiModel(
    var credit_id: String ?= "",
    var id: Int ?=0,
    var job: String ?="",
    var known_for_department: String ?= "",
    var name: String ?= "",
    var profile_path: String ?= ""
)