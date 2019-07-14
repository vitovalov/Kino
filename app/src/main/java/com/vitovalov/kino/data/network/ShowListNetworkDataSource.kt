package com.vitovalov.kino.data.network

import com.vitovalov.kino.data.ShowListDataSource
import com.vitovalov.kino.data.mapper.ShowListDtoMapper
import com.vitovalov.kino.domain.model.ShowBo

class ShowListNetworkDataSource(
    private val showListApi: MovieDbApi,
    private val showListDtoMapper: ShowListDtoMapper
) : ShowListDataSource {

    override suspend fun getShowList(page: Int): List<ShowBo> =
        showListDtoMapper.toBo(showListApi.getShowList(NetworkConfig.API_KEY, NetworkConfig.API_LANG, page))
}
