package com.vitovalov.kino.data.local

import com.vitovalov.kino.data.ShowListDataSource
import com.vitovalov.kino.domain.model.ShowBo

interface ShowListLocalDataSource : ShowListDataSource {

    suspend fun saveShowList(items: List<ShowBo>, page: Int)
}