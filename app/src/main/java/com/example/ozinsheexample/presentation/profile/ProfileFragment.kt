package com.example.ozinsheexample.presentation.profile

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import com.example.ozinsheexample.data.SharedProvider
import com.example.ozinsheexample.databinding.FragmentProfileBinding
import com.example.ozinsheexample.presentation.profile.bottomsheet.SelectLanguageBottomSheet
import java.util.Locale

class ProfileFragment : Fragment(), OnLanguageSelectedListener {

    private lateinit var binding: FragmentProfileBinding
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        systemLanguage()

        val transaction:FragmentTransaction = parentFragmentManager.beginTransaction()
        if (Build.VERSION.SDK_INT >= 26) {
            transaction.setReorderingAllowed(false)
        }
        transaction.detach(this).attach(this).commit()

        viewModel.language.observe(viewLifecycleOwner) {
            binding.tvSelectedLanguage.text = it
        }

        binding.btnChangeLanguage.setOnClickListener {
            val bottomSheet = SelectLanguageBottomSheet()
            bottomSheet.setOnLanguageSelectedListener(this)
            bottomSheet.show(parentFragmentManager, bottomSheet.tag)
        }
    }

    override fun onLanguageSelected(language: String) {
        viewModel.selectLanguage(language)
    }

    private fun systemLanguage(){
        when(SharedProvider(requireContext()).getLanguage()) {
            "kk" -> {
                val local = Locale("kk")
                Locale.setDefault(local)
                val config = resources.configuration
                config.setLocale(local)
                requireContext().resources.updateConfiguration(config, requireContext().resources.displayMetrics)
                binding.tvSelectedLanguage.text = "Қазақша"
            }
            "ru" -> {
                val local = Locale("ru")
                Locale.setDefault(local)
                val config = resources.configuration
                config.setLocale(local)
                requireContext().resources.updateConfiguration(config, requireContext().resources.displayMetrics)
                binding.tvSelectedLanguage.text = "Русский"
            }
            "en" -> {
                val local = Locale("en")
                Locale.setDefault(local)
                val config = resources.configuration
                config.setLocale(local)
                requireContext().resources.updateConfiguration(config, requireContext().resources.displayMetrics)
                binding.tvSelectedLanguage.text = "English"
            }
            else -> {
                val local = Locale("kk")
                Locale.setDefault(local)
                val config = resources.configuration
                config.setLocale(local)
                requireContext().resources.updateConfiguration(config, requireContext().resources.displayMetrics)
                binding.tvSelectedLanguage.text = "Қазақша"
            }
        }
//        val language = resources.configuration.locales[0].language
//        Log.d("ProfileFragment", "systemLanguage: $language")
//        viewModel.selectLanguage(language)
    }
}