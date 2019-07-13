package com.vitovalov.kino.data.mapper

import com.vitovalov.kino.data.network.model.ShowDto
import com.vitovalov.kino.data.network.model.ShowListDto
import com.vitovalov.kino.domain.model.ShowBo

class ShowListDtoMapper {
    fun toBo(dto: ShowListDto): List<ShowBo> = dto.results.map { toBo(it) }

    fun toBo(dto: ShowDto): ShowBo =
        ShowBo(
            dto.backdrop_path,
            dto.id,
            dto.name,
            dto.origin_country,
            dto.original_name,
            dto.overview,
            dto.popularity,
            dto.poster_path,
            dto.vote_average,
            dto.vote_count
        )
}
