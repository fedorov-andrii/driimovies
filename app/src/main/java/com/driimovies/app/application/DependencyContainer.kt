package com.driimovies.app.application

import com.driimovies.app.ui.common.ErrorMessageFactory
import com.driimovies.app.ui.container.Router
import com.driimovies.kit.repositories.AuthenticationRepository
import com.driimovies.kit.repositories.MovieRepository
import com.driimovies.network.interseptors.ApiKeyInterceptor
import com.driimovies.network.services.MovieService
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit



class DependencyContainer(private val application: App) {

    val router: Router = application
    val errorMessageFactory by lazy { ErrorMessageFactory(application) }
    val authenticationRepository by lazy { AuthenticationRepository() }
    val movieRepository by lazy { MovieRepository(movieService) }

    val imageBaseUrl = "https://image.tmdb.org/t/p/w400/"
    private val apiKey = "bfe0311c0a1233f20612ee02b38527f2"
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(ApiKeyInterceptor(apiKey))
        .build()

    private val gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient)
        .build()

    private val movieService = retrofit.create(MovieService::class.java)

}