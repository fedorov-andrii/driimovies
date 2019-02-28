package com.driimovies.core

sealed class AppException(message: String = "Something bad happen", cause: Throwable? = null) :
    Exception(message, cause) {

    class CredentialsNotValidException: AppException()
    class UserAlreadyExistException: AppException()
    class UserNotExistExistException: AppException()

}