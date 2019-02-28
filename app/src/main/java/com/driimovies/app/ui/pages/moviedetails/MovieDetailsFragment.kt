package com.driimovies.app.ui.pages.moviedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.driimovies.R
import com.driimovies.core.models.Movie
import com.driimovies.databinding.FragmentMovieDetailsBinding
import com.driimovies.extension.asApp

class MovieDetailsFragment : Fragment() {

    companion object {
        private val MOVIE_KEY = "movie_key"

        fun newInstance(movie: Movie) = MovieDetailsFragment().apply {
            arguments = bundleOf(MOVIE_KEY to movie)
        }
    }

    private val movie: Movie by lazy { arguments?.getParcelable(MOVIE_KEY) as? Movie ?: throw IllegalArgumentException() }
    private lateinit var binding: FragmentMovieDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        binding.setLifecycleOwner(this)
        binding.toolbar.setNavigationOnClickListener { activity?.onBackPressed() }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModelFactory = MovieDetailsViewModelFactory(movie, asApp().dependencyContainer)
        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieDetailsViewModel::class.java)
        binding.viewModel = viewModel

        Glide.with(this)
            .load(viewModel.movie.posterPath)
            .into(binding.poster)
    }

}
