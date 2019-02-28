package com.driimovies.network

import com.driimovies.core.models.Movie

data class PageResponse(
    val page: Int,
    val results: List<Movie>,
    val totalResults: Int,
    val totalPages: Int
)