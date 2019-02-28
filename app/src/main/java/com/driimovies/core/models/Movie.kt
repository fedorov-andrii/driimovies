package com.driimovies.core.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val posterPath: String?,
    val originalTitle: String,
    val voteAverage: Double,
    val releaseDate: String,
    val overview: String,
    val originalLanguage: String,
    val popularity: Double
): Parcelable