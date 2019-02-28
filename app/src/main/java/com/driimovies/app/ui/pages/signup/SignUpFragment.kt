package com.driimovies.app.ui.pages.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.driimovies.databinding.FragmentSignUpBinding
import com.driimovies.extension.asApp

class SignUpFragment : Fragment() {

    companion object {
        fun newInstance() = SignUpFragment()
    }

    private lateinit var binding: FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        binding.setLifecycleOwner(this)
        binding.toolbar.setNavigationOnClickListener { activity?.onBackPressed() }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModelFactory = SignUpViewModelFactory(asApp().dependencyContainer)
        binding.viewModel = ViewModelProviders.of(this, viewModelFactory).get(SignUpViewModel::class.java)
    }

}
