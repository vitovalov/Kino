package com.vitovalov.kino.data.network.model

data class ShowListDto(
    val page: Int,
    val results: List<ShowDto>,
    val total_pages: Int,
    val total_results: Int
)

