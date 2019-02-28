package com.driimovies.app.ui.pages.moviedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.driimovies.app.application.DependencyContainer
import com.driimovies.core.models.Movie

class MovieDetailsViewModelFactory(
    private val movie: Movie,
    private val dependencyContainer: DependencyContainer
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieDetailsViewModel::class.java)) {

            val router = dependencyContainer.router

            @Suppress("UNCHECKED_CAST")
            return MovieDetailsViewModel(
                router,
                movie
            ) as T
        }

        throw IllegalArgumentException("View model $modelClass is not supported")
    }
}