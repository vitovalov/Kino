package com.vitovalov.kino.data.network.model

data class ShowDto(
    val backdrop_path: String,
    val id: Int,
    val name: String,
    val origin_country: List<String>,
    val original_name: String,
    val overview: String?,
    val popularity: Double,
    val poster_path: String,
    val vote_average: Double,
    val vote_count: Int
)
