package com.driimovies.app.ui.container

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.driimovies.R
import com.driimovies.app.ui.pages.main.MainFragment
import com.driimovies.app.ui.pages.moviedetails.MovieDetailsFragment
import com.driimovies.app.ui.pages.signin.SignInFragment
import com.driimovies.app.ui.pages.signup.SignUpFragment
import com.driimovies.app.ui.pages.userprofile.UserProfileFragment
import com.driimovies.extension.asApp

class ContainerActivity : AppCompatActivity() {

    private lateinit var viewModel: ContainerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        initViewModel()

        viewModel.onChangePageEvent.observe(this, Observer { handleChangePageEvent(it) })
    }

    private fun initViewModel() {
        val viewModelFactory = ContainerViewModelFactory(asApp().dependencyContainer)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ContainerViewModel::class.java)

        val rootChangeEvent = asApp().onChangePageEvent
        rootChangeEvent.observe(this, Observer { viewModel.onChangePageEvent.postValue(it) })
    }

    private fun handleChangePageEvent(designation: Destination) = when (designation) {
        is Destination.SignIn -> showSignInPage(designation)
        is Destination.SignUp -> showSignUpPage(designation)
        is Destination.Main -> showMainPage(designation)
        is Destination.MovieDetails -> showMovieDetailsPage(designation)
        is Destination.UserProfile -> showUserProfilePage(designation)
    }

    private fun showSignInPage(designation: Destination.SignIn) {
        val signInFragment = SignInFragment.newInstance()

        supportFragmentManager.popBackStack(null, 0)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, signInFragment)
        }.commit()
    }

    private fun showSignUpPage(designation: Destination.SignUp) {
        val signUpFragment = SignUpFragment.newInstance()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, signUpFragment)
            addToBackStack(null)
        }.commit()
    }

    private fun showMainPage(designation: Destination.Main) {
        val mainFragment = MainFragment.newInstance()

        supportFragmentManager.popBackStack(null, 0)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, mainFragment)
        }.commit()
    }

    private fun showMovieDetailsPage(designation: Destination.MovieDetails) {
        val movieDetailsFragment = MovieDetailsFragment.newInstance(designation.movie)

        supportFragmentManager.beginTransaction().apply {
            add(R.id.container, movieDetailsFragment)
            addToBackStack(null)
        }.commit()
    }

    private fun showUserProfilePage(designation: Destination.UserProfile) {
        val userProfileFragment = UserProfileFragment.newInstance()

        supportFragmentManager.beginTransaction().apply {
            add(R.id.container, userProfileFragment)
            addToBackStack(null)
        }.commit()
    }

}
