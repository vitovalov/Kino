package com.vitovalov.kino.domain

import com.vitovalov.kino.data.ShowListDataSource
import com.vitovalov.kino.data.local.ShowListLocalDataSource
import com.vitovalov.kino.domain.model.ShowBo

class ShowListRepository(
    private val showListDataSource: ShowListDataSource,
    private val showListLocalDataSource: ShowListLocalDataSource
) {

    suspend fun getShowList(page: Int): List<ShowBo> {
        var items: List<ShowBo>
        items = try {
            showListLocalDataSource.getShowList(page)
        } catch (e: Exception) {
            loadRemoteItemsAndCache(page)
        }
        if (items.isEmpty()) {
            items = loadRemoteItemsAndCache(page)
        }

        return items
    }

    private suspend fun loadRemoteItemsAndCache(
        page: Int
    ): List<ShowBo> {
        val items: List<ShowBo> = showListDataSource.getShowList(page)
        showListLocalDataSource.saveShowList(items, page)
        return items
    }
}
