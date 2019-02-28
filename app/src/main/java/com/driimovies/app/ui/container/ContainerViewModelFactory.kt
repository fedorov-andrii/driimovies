package com.driimovies.app.ui.container

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.driimovies.app.application.DependencyContainer

class ContainerViewModelFactory(
    private val dependencyContainer: DependencyContainer
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ContainerViewModel::class.java)) {

            val authenticationRepository = dependencyContainer.authenticationRepository

            @Suppress("UNCHECKED_CAST")
            return ContainerViewModel(authenticationRepository) as T
        }

        throw IllegalArgumentException("View model $modelClass is not supported")
    }
}