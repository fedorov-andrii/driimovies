package com.driimovies.network.interseptors

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor(
    private val apiKey: String
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        val newUrl = request()
            .url()
            .newBuilder()
            .addQueryParameter("api_key", apiKey)
            .build()

        proceed(
            request()
                .newBuilder()
                .url(newUrl)
                .build()
        )
    }
}
