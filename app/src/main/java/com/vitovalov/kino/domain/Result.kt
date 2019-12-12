package com.vitovalov.kino.domain

data class Result<out T>(val status: Status, val data: T?, val error: Failure?) {

    enum class Status {
        SUCCESS,
        ERROR,
    }

    companion object {
        fun <T> success(data: T): Result<T> {
            return Result(Status.SUCCESS, data, null)
        }

        fun <T> error(error: Failure, data: T? = null): Result<T> {
            return Result(Status.ERROR, data, error)
        }
    }
}