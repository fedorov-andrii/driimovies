package com.driimovies.app.ui.pages.userprofile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.driimovies.databinding.FragmentUserProfileBinding
import com.driimovies.extension.asApp

class UserProfileFragment : Fragment() {

    companion object {
        fun newInstance() = UserProfileFragment()
    }

    private lateinit var binding: FragmentUserProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserProfileBinding.inflate(inflater, container, false)
        binding.setLifecycleOwner(this)
        binding.toolbar.setNavigationOnClickListener { activity?.onBackPressed() }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModelFactory = UserProfileViewModelFactory(asApp().dependencyContainer)
        binding.viewModel = ViewModelProviders.of(this, viewModelFactory).get(UserProfileViewModel::class.java)
    }

}
