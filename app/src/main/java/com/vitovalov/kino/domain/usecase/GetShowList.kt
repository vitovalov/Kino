package com.vitovalov.kino.domain.usecase

import com.vitovalov.kino.domain.Either
import com.vitovalov.kino.domain.Failure
import com.vitovalov.kino.domain.BaseUseCase
import com.vitovalov.kino.domain.ShowListRepository
import com.vitovalov.kino.domain.model.ShowBo


class GetShowList(
    private val showListRepository: ShowListRepository
) : BaseUseCase<List<ShowBo>, GetShowList.Params>() {

    override suspend fun run(params: Params): Either<Failure, List<ShowBo>> {
        return try {
            val items = showListRepository.getShowList(params.page)
            Either.Right(items)
        } catch (exp: Exception) {
            Either.Left(Failure.Error(exp))
        }
    }

    data class Params(val page: Int)
}
