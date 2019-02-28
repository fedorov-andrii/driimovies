package com.driimovies.kit.repositories

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.driimovies.core.models.Movie
import com.driimovies.kit.datasources.MovieDataSource
import com.driimovies.network.services.MovieService

class MovieRepository(private val movieService: MovieService) {

    fun getMovies(): LiveData<PagedList<Movie>> {

        return LivePagedListBuilder(object : DataSource.Factory<Int, Movie>() {
            override fun create() = MovieDataSource(movieService)
        }, 1)
            .build()
    }

}