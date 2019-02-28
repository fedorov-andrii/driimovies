package com.driimovies.app.ui.common

import android.app.Application
import com.driimovies.R
import com.driimovies.core.AppException

class ErrorMessageFactory(private val application: Application) {

    fun getError(e: Throwable): String {
        return when(e) {
            is AppException.CredentialsNotValidException -> application.getString(R.string.ErrorMessage_credentialsNotValid)
            is AppException.UserAlreadyExistException -> application.getString(R.string.ErrorMessage_userAlreadyExist)
            is AppException.UserNotExistExistException -> application.getString(R.string.ErrorMessage_userNotExistExist)
            else -> e.message ?: "Something bad happen"
        }
    }

}