package com.driimovies.app.ui.container

import com.driimovies.core.models.Movie

sealed class Destination {

    object SignIn : Destination()
    object SignUp : Destination()
    object Main : Destination()
    class MovieDetails(val movie: Movie) : Destination()
    object UserProfile : Destination()

}