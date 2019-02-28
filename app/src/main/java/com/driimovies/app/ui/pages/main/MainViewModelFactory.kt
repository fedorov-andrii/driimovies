package com.driimovies.app.ui.pages.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.driimovies.app.application.DependencyContainer

class MainViewModelFactory(
    private val dependencyContainer: DependencyContainer
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {

            val router = dependencyContainer.router
            val authenticationRepository = dependencyContainer.authenticationRepository
            val movieRepository = dependencyContainer.movieRepository
            val errorMessageFactory = dependencyContainer.errorMessageFactory

            @Suppress("UNCHECKED_CAST")
            return MainViewModel(
                router,
                authenticationRepository,
                movieRepository,
                errorMessageFactory
            ) as T
        }

        throw IllegalArgumentException("View model $modelClass is not supported")
    }
}