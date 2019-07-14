package com.vitovalov.kino.data.local.mapper

import com.vitovalov.kino.data.local.model.ShowLo
import com.vitovalov.kino.domain.model.ShowBo

class ShowLoMapper {

    fun toBo(items: List<ShowLo>): List<ShowBo> = items.map { toBo(it) }

    fun toBo(lo: ShowLo): ShowBo =
        ShowBo(
            lo.backdropPath,
            lo.id,
            lo.name,
            lo.voteAverage
        )

    fun toLo(items: List<ShowBo>, page: Int): List<ShowLo> =
        items.map { toLo(it, page) }

    private fun toLo(lo: ShowBo, page: Int): ShowLo =
        ShowLo(
            lo.id,
            page,
            lo.backdropPath,
            lo.name,
            lo.voteAverage
        )
}
