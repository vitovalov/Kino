package com.vitovalov.kino.data.local.mapper

import com.vitovalov.kino.data.local.model.ShowLo
import com.vitovalov.kino.domain.model.Show

class ShowLoMapper {

    fun toBo(items: List<ShowLo>): List<Show> = items.map { toBo(it) }

    fun toBo(lo: ShowLo): Show =
        Show(
            lo.backdropPath,
            lo.id,
            lo.title,
            lo.voteAverage
        )

    fun toLo(items: List<Show>, page: Int): List<ShowLo> =
        items.map { toLo(it, page) }

    private fun toLo(lo: Show, page: Int): ShowLo =
        ShowLo(
            lo.id,
            page,
            lo.backdropPath,
            lo.name,
            lo.voteAverage
        )
}
