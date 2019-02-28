package com.driimovies.app.ui.pages.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.driimovies.app.application.DependencyContainer

class SignUpViewModelFactory(
    private val dependencyContainer: DependencyContainer
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignUpViewModel::class.java)) {

            val router = dependencyContainer.router
            val authenticationRepository = dependencyContainer.authenticationRepository
            val errorMessageFactory = dependencyContainer.errorMessageFactory

            @Suppress("UNCHECKED_CAST")
            return SignUpViewModel(router, authenticationRepository, errorMessageFactory) as T
        }

        throw IllegalArgumentException("View model $modelClass is not supported")
    }
}