package com.driimovies.app.ui.pages.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
import com.bumptech.glide.request.RequestOptions
import com.driimovies.R
import com.driimovies.app.ui.utils.BindingHolder
import com.driimovies.core.models.Movie
import com.driimovies.databinding.ItemMovieListModeBinding
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate

class MovieListModeDelegate(
    private val inflater: LayoutInflater,
    private val isListMode: () -> Boolean,
    private val onClick: (Movie) -> Unit
) : AdapterDelegate<List<Movie>>() {

    override fun isForViewType(items: List<Movie>, position: Int) = isListMode.invoke()

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return BindingHolder.newInstance<ItemMovieListModeBinding>(
            R.layout.item_movie_list_mode,
            inflater,
            parent,
            false
        )
    }

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(
        items: List<Movie>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        (holder as BindingHolder<ItemMovieListModeBinding>).binding.apply {
            movie = items[position]
            root.setOnClickListener { onClick.invoke(items[position]) }
            executePendingBindings()

            Glide.with(root)
                .load(items[position].posterPath)
                .into(poster)
        }
    }

}