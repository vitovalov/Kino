package com.vitovalov.kino.ui.model

data class ShowUo(
    val backdropPath: String,
    val id: Int,
    val name: String,
    val originCountry: List<String>,
    val originalName: String,
    val overview: String?,
    val popularity: Double,
    val posterPath: String?,
    val voteAverage: Double,
    val voteCount: Int
)
