package com.vitovalov.kino.ui.mapper

import com.vitovalov.kino.domain.model.ShowBo
import com.vitovalov.kino.ui.model.ShowUo

class ShowListUoMapper {

    fun toUo(items: List<ShowBo>): List<ShowUo> = items.map { toUo(it) }

    fun toUo(bo: ShowBo): ShowUo {
        return ShowUo(
            bo.backdropPath,
            bo.id,
            bo.name,
            bo.voteAverage
        )
    }
}
