package com.driimovies.kit.datasources

import androidx.paging.PageKeyedDataSource
import com.driimovies.core.models.Movie
import com.driimovies.network.PageResponse
import com.driimovies.network.services.MovieService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDataSource(private val movieService: MovieService) : PageKeyedDataSource<Int, Movie>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Movie>
    ) {
        movieService.listRepos(page = 1)
            .enqueue(object : Callback<PageResponse> {
                override fun onResponse(
                    call: Call<PageResponse>,
                    response: Response<PageResponse>
                ) {
                    val body = response.body()
                    if (response.isSuccessful && body != null) {
                        val nextPage = if (1 == body.totalPages) null else 2
                        callback.onResult(body.results, null, nextPage)
                    }
                }

                override fun onFailure(call: Call<PageResponse>, t: Throwable) {
                }
            })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        movieService.listRepos(page = params.key)
            .enqueue(object : Callback<PageResponse> {
                override fun onResponse(
                    call: Call<PageResponse>,
                    response: Response<PageResponse>
                ) {
                    val body = response.body()
                    if (response.isSuccessful && body != null) {
                        val nextPage = if (params.key == body.totalPages) null else params.key + 1
                        callback.onResult(body.results, nextPage)
                    }
                }

                override fun onFailure(call: Call<PageResponse>, t: Throwable) {
                }
            })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
    }
}