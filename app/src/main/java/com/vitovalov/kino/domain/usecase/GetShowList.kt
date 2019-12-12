package com.vitovalov.kino.domain.usecase

import com.vitovalov.kino.domain.BaseUseCase
import com.vitovalov.kino.domain.ShowListRepository
import com.vitovalov.kino.domain.model.ShowBo

class GetShowList(
    private val showListRepository: ShowListRepository
) : BaseUseCase<List<ShowBo>, GetShowList.Params>() {

    override suspend fun run(params: Params) = showListRepository.getShowList(params.page)

    data class Params(val page: Int)
}
