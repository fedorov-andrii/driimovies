package com.driimovies.app.ui.pages.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.driimovies.app.ui.common.ErrorMessageFactory
import com.driimovies.app.ui.container.Destination
import com.driimovies.app.ui.container.Router
import com.driimovies.core.models.Movie
import com.driimovies.kit.repositories.AuthenticationRepository
import com.driimovies.kit.repositories.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainViewModel(
    private val router: Router,
    private val authenticationRepository: AuthenticationRepository,
    private val movieRepository: MovieRepository,
    private val errorMessageFactory: ErrorMessageFactory
) : ViewModel(), CoroutineScope {


    val movies: LiveData<PagedList<Movie>> = movieRepository.getMovies()

    private val parentJob = Job()
    override val coroutineContext = Dispatchers.Default + parentJob

    fun showProfile() {
        router.route(Destination.UserProfile)
    }

    fun signOut() = launch {
        authenticationRepository.signOut()
        router.route(Destination.SignIn)
    }

    fun showMovieDetails(movie: Movie) {
        router.route(Destination.MovieDetails(movie))
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }

}
