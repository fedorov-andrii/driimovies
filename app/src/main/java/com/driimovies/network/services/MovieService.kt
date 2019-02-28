package com.driimovies.network.services

import com.driimovies.network.PageResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface MovieService {

    @GET("3/discover/movie")
    fun listRepos(
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("page") page: Int = 1
    ): Call<PageResponse>

}