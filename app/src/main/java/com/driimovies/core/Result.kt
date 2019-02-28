package com.driimovies.core

sealed class Result<out T, out E> {

    data class Success<T>(val value: T) : Result<T, Nothing>()

    data class Error<E>(val error: E) : Result<Nothing, E>()

}
