package com.example.ozinsheexample.presentation.about

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.ozinsheexample.R
import com.example.ozinsheexample.data.SharedProvider
import com.example.ozinsheexample.databinding.FragmentAboutBinding
import com.example.ozinsheexample.provideNavigationHost

class AboutFragment : Fragment() {
    private lateinit var binding: FragmentAboutBinding
    private val args: AboutFragmentArgs by navArgs()
    private val viewModel: AboutViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAboutBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        provideNavigationHost()?.apply {
            setNavigationVisibility(false)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        provideNavigationHost()?.apply {
            setNavigationVisibility(false)
        }
        Toast.makeText(requireContext(), "${args.movieId}", Toast.LENGTH_SHORT).show()
        val token = SharedProvider(requireContext()).getToken()
        viewModel.getMoviesById(token, args.movieId)

        viewModel.moviesByIdResponse.observe(viewLifecycleOwner){
            Glide.with(requireContext())
                .load(it.poster.link)
                .into(binding.imgPoster)
            binding.textTvTittleMovie.text = it.name
            binding.textTvAdditionalInfoYear.text = it.year.toString()
            binding.textTvGenres.text = it.genres.joinToString { it.name }
            binding.textTvDescription.text = it.description
            binding.textTvDirector.text = it.director
            binding.textTvProducer.text = it.producer
            binding.textTvBolimder.text = it.seriesCount.toString()
        }
        viewModel.errorResponse.observe(viewLifecycleOwner){
            Log.d("AAA", it)
        }
    }
}
