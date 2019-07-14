package com.vitovalov.kino.domain

import com.vitovalov.kino.data.ShowListDataSource
import com.vitovalov.kino.data.local.ShowListLocalDataSource
import com.vitovalov.kino.domain.model.ShowBo

class ShowListRepository(
    private val showListDataSource: ShowListDataSource,
    private val showListLocalDataSource: ShowListLocalDataSource
) {

    suspend fun getShowList(page: Int): List<ShowBo> {
        var items = showListLocalDataSource.getShowList(page)

        if (items.isEmpty()) {
            items = showListDataSource.getShowList(page)
            showListLocalDataSource.saveShowList(items, page)
        }

        return items
    }
}
