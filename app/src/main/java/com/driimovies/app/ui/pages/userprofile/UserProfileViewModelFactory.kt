package com.driimovies.app.ui.pages.userprofile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.driimovies.app.application.DependencyContainer

class UserProfileViewModelFactory(
    private val dependencyContainer: DependencyContainer
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserProfileViewModel::class.java)) {

            val router = dependencyContainer.router
            val authenticationRepository = dependencyContainer.authenticationRepository

            @Suppress("UNCHECKED_CAST")
            return UserProfileViewModel(router, authenticationRepository) as T
        }

        throw IllegalArgumentException("View model $modelClass is not supported")
    }
}