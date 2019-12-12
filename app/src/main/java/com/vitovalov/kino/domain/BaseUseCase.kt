package com.vitovalov.kino.domain

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

abstract class BaseUseCase<out Type, in Params> where Type : Any {

    abstract suspend fun run(params: Params): Result<Type>

    open operator fun invoke(
        scope: CoroutineScope,
        params: Params,
        onResult: (Result<Type>) -> Unit = {}
    ) {
        val backgroundJob = scope.async(Dispatchers.IO) { run(params) }
        scope.launch { onResult(backgroundJob.await()) }
    }
}
