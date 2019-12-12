package com.vitovalov.kino.data.mapper

import com.vitovalov.kino.data.network.NetworkConfig
import com.vitovalov.kino.data.network.model.ShowDto
import com.vitovalov.kino.data.network.model.ShowListDto
import com.vitovalov.kino.domain.model.Show

class ShowListDtoMapper {
    fun toBo(dto: ShowListDto): List<Show> = dto.results.map {
        toBo(it)
    }

    fun toBo(dto: ShowDto): Show =
        Show(
            completeBackdrop(dto.backdrop_path),
            dto.id,
            dto.name,
            dto.vote_average
        )

    private fun completeBackdrop(url: String) =
        NetworkConfig.API_BASE_IMAGE + NetworkConfig.API_ORIGINAL_IMAGE + url
}
