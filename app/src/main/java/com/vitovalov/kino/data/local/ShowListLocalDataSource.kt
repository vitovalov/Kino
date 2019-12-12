package com.vitovalov.kino.data.local

import com.vitovalov.kino.data.ShowListDataSource
import com.vitovalov.kino.domain.model.Show

interface ShowListLocalDataSource : ShowListDataSource {

    suspend fun saveShowList(items: List<Show>, page: Int)
}