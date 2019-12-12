package com.vitovalov.kino.data

import com.vitovalov.kino.domain.model.Show

interface ShowListDataSource {

    suspend fun getShowList(page: Int): List<Show>
}