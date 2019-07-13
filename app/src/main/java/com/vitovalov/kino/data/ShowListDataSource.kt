package com.vitovalov.kino.data

import com.vitovalov.kino.domain.model.ShowBo

interface ShowListDataSource {

    suspend fun getShowList(page: Int): List<ShowBo>
}