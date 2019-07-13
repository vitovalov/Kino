package com.vitovalov.kino.domain

sealed class Failure {
    data class Error(val value: Exception) : Failure()
}
