package com.driimovies.app.ui.pages.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.driimovies.app.application.DependencyContainer

class SignInViewModelFactory(
    private val dependencyContainer: DependencyContainer
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignInViewModel::class.java)) {

            val router = dependencyContainer.router
            val authenticationRepository = dependencyContainer.authenticationRepository
            val errorMessageFactory = dependencyContainer.errorMessageFactory

            @Suppress("UNCHECKED_CAST")
            return SignInViewModel(router, authenticationRepository, errorMessageFactory) as T
        }

        throw IllegalArgumentException("View model $modelClass is not supported")
    }
}