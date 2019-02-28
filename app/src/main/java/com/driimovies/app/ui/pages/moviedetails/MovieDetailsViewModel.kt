package com.driimovies.app.ui.pages.moviedetails

import androidx.lifecycle.ViewModel
import com.driimovies.app.ui.container.Router
import com.driimovies.core.models.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class MovieDetailsViewModel(
    private val router: Router,
    val movie: Movie
) : ViewModel(), CoroutineScope {

    private val parentJob = Job()
    override val coroutineContext = Dispatchers.Default + parentJob


    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }

}
