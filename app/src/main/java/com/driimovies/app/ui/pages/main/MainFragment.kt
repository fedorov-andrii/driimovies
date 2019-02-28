package com.driimovies.app.ui.pages.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.driimovies.app.ui.utils.PagedListDelegationAdapter
import com.driimovies.core.models.Movie
import com.driimovies.databinding.FragmentMainBinding
import com.driimovies.extension.asApp
import com.google.android.material.tabs.TabLayout
import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable
import androidx.core.view.GravityCompat
import com.driimovies.R


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var binding: FragmentMainBinding
    private lateinit var adapter: PagedListDelegationAdapter<Movie>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.setLifecycleOwner(this)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initToolbar()
        initNavigationMenuListener()

        val viewModelFactory = MainViewModelFactory(asApp().dependencyContainer)
        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        binding.viewModel = viewModel

        binding.movies.layoutManager = GridLayoutManager(context, 1)
        binding.viewModes.addOnTabSelectedListener(provideOnTabSelectedListener())

        val delegateManager = prepareAdapterDelegateManager(viewModel)
        adapter = PagedListDelegationAdapter(delegateManager, MovieDiffCallback())
        viewModel.movies.observe(this, Observer { adapter.submitList(it) })
        binding.movies.adapter = adapter
    }

    private fun initToolbar() {
        binding.toolbar.navigationIcon = DrawerArrowDrawable(context)
        binding.toolbar.setNavigationOnClickListener { binding.drawerLayout.openDrawer(GravityCompat.START)}
    }

    private fun initNavigationMenuListener() {
        binding.navigationMenu.setNavigationItemSelectedListener {
            binding.drawerLayout.closeDrawers()

            when(it.itemId) {
                R.id.showProfile -> binding.viewModel?.showProfile()
                R.id.signOut -> binding.viewModel?.signOut()
            }

            return@setNavigationItemSelectedListener true
        }
    }

    private fun prepareAdapterDelegateManager(viewModel: MainViewModel): AdapterDelegatesManager<List<Movie>> {
        val inflater = LayoutInflater.from(context)
        return AdapterDelegatesManager<List<Movie>>()
            .addDelegate(
                MovieListModeDelegate(
                    inflater,
                    ::isListMode,
                    viewModel::showMovieDetails
                )
            ).addDelegate(
                MovieTableModeDelegate(
                    inflater,
                    { !isListMode() },
                    viewModel::showMovieDetails
                )
            )
    }

    private fun provideOnTabSelectedListener() = object : TabLayout.OnTabSelectedListener {
        override fun onTabReselected(tab: TabLayout.Tab?) {
        }

        override fun onTabUnselected(tab: TabLayout.Tab?) {
        }

        override fun onTabSelected(tab: TabLayout.Tab?) {
            val newSpanCount = if (tab?.position == 0) 1 else 3
            (binding.movies.layoutManager as? GridLayoutManager)?.spanCount = newSpanCount
            adapter.notifyDataSetChanged()

        }
    }

    private fun isListMode() = (binding.movies.layoutManager as? GridLayoutManager)?.spanCount == 1

}
