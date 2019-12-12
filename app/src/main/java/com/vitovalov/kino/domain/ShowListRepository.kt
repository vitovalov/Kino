package com.vitovalov.kino.domain

import com.vitovalov.kino.data.ShowListDataSource
import com.vitovalov.kino.data.local.ShowListLocalDataSource
import com.vitovalov.kino.domain.model.ShowBo

class ShowListRepository(
    private val showListDataSource: ShowListDataSource,
    private val showListLocalDataSource: ShowListLocalDataSource
) {

    suspend fun getShowList(page: Int): Result<List<ShowBo>> {
        var items: List<ShowBo>

        try {
            println("trying to load showList from remote ds")
            items = showListDataSource.getShowList(page)

            if (items.isNotEmpty()) {
                println("saving new data [${items.size}] to local db")
                showListLocalDataSource.saveShowList(items, page)
            }
        } catch (e: Exception) {
            println("first catch $e")
            return try {
                println("trying to load showList from local ds")
                items = showListLocalDataSource.getShowList(page)
                Result.error(Failure.Error(e), items)
            } catch (e: Exception) {
                println("exception $e during local ds showList retrieval")
                Result.error(Failure.Error(e))
            }
        }

        return Result.success(items)
    }
}
