package com.vitovalov.kino.domain

import com.vitovalov.kino.data.ShowListDataSource
import com.vitovalov.kino.domain.model.ShowBo

class ShowListRepository(
    private val showListDataSource: ShowListDataSource
) {

    suspend fun getShowList(page: Int): List<ShowBo> {
        val showList = showListDataSource.getShowList(page)

        return showList
    }
}
