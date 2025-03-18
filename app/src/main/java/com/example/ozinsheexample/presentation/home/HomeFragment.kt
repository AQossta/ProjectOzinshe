package com.example.ozinsheexample.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ozinsheexample.R
import com.example.ozinsheexample.data.SharedProvider
import com.example.ozinsheexample.databinding.FragmentHomeBinding
import com.example.ozinsheexample.provideNavigationHost

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        provideNavigationHost()?.apply {
            setNavigationVisibility(true)
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        provideNavigationHost()?.apply {
            setNavigationVisibility(true)
        }

        val token = SharedProvider(requireContext()).getToken()
        viewModel.getMainMovies(token)
        val adapterMainMovie = MainMovieAdapter()
        binding.rcMainMovies.adapter = adapterMainMovie
        adapterMainMovie.setOnMovieClickListener(object :RcViewItemClickMainMoviesCallback{
            override fun onClick(movieId: Int) {
                val action = HomeFragmentDirections.actionHomeFragmentToAboutFragment(movieId)
                findNavController().navigate(action)
            }

        })
        viewModel.mainMoviesResponse.observe(viewLifecycleOwner) {
            adapterMainMovie.sumbitList(it)
        }

        viewModel.getMoviesByCategoryMain(token)
        val adapterMoviesByCategory1 = MoviesByCategoryMainAdapter()
        adapterMoviesByCategory1.setOnMovieClickListener(object :RcViewItemClickMainMoviesCallback{
            override fun onClick(movieId: Int) {
                val action = HomeFragmentDirections.actionHomeFragmentToAboutFragment(movieId)
                findNavController().navigate(action)
            }
        })
        viewModel.moviesByCategoryMainModel.observe(viewLifecycleOwner) {
            binding.rcMainCategories1.adapter = adapterMoviesByCategory1
            binding.tvCategoryTitle1Text.text = it[0].categoryName
            adapterMoviesByCategory1.sumbitList(it[0].movies)
        }

        viewModel.getMoviesByCategoryMain(token)
        val adapterMoviesByCategory2 = MoviesByCategoryMainAdapter()
        adapterMoviesByCategory2.setOnMovieClickListener(object :RcViewItemClickMainMoviesCallback{
            override fun onClick(movieId: Int) {
                val action = HomeFragmentDirections.actionHomeFragmentToAboutFragment(movieId)
                findNavController().navigate(action)
            }
        })
        viewModel.moviesByCategoryMainModel.observe(viewLifecycleOwner) {
            binding.rcMainCategories2.adapter = adapterMoviesByCategory2
            binding.tvCategoryTitle2Text.text = it[1].categoryName
            adapterMoviesByCategory2.sumbitList(it[1].movies)
        }

        viewModel.getMoviesByCategoryMain(token)
        val adapterMoviesByCategory3 = MoviesByCategoryMainAdapter()
        adapterMoviesByCategory3.setOnMovieClickListener(object :RcViewItemClickMainMoviesCallback{
            override fun onClick(movieId: Int) {
                val action = HomeFragmentDirections.actionHomeFragmentToAboutFragment(movieId)
                findNavController().navigate(action)
            }
        })
        viewModel.moviesByCategoryMainModel.observe(viewLifecycleOwner) {
            binding.rcMainCategories3.adapter = adapterMoviesByCategory3
            binding.tvCategoryTitle3Text.text = it[2].categoryName
            adapterMoviesByCategory3.sumbitList(it[2].movies)
        }
    }
}