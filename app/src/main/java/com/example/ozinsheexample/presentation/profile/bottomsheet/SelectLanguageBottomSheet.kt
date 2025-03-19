package com.example.ozinsheexample.presentation.profile.bottomsheet

import android.content.res.Configuration
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.ozinsheexample.R
import com.example.ozinsheexample.data.SharedProvider
import com.example.ozinsheexample.databinding.BottomsheetSelectLanguageBinding
import com.example.ozinsheexample.presentation.profile.OnLanguageSelectedListener
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.Locale

class SelectLanguageBottomSheet : BottomSheetDialogFragment() {
    private lateinit var binding: BottomsheetSelectLanguageBinding
    private var languageSelectedListener: OnLanguageSelectedListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BottomsheetSelectLanguageBinding.inflate(inflater, container, false)
        return binding.root
    }

    fun setOnLanguageSelectedListener(listener: OnLanguageSelectedListener) {
        languageSelectedListener = listener
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val defaultLanguage: String = SharedProvider(requireContext()).getLanguage()
        when (defaultLanguage) {
            "en" -> {
                selectedIcon(true, false, false)
            }

            "kk" -> {
                selectedIcon(false, true, false)
            }

            "ru" -> {
                selectedIcon(false, false, true)
            }
        }

        binding.apply {
            btnSelectEnglish.setOnClickListener {
                selectedIcon(true, false, false)
                changeLanguage("English")
            }
            btnSelectKazakh.setOnClickListener {
                selectedIcon(false, true, false)
                changeLanguage("Kazakh")
            }
            btnSelectRussia.setOnClickListener {
                selectedIcon(false, false, true)
                changeLanguage("Russian")
            }
        }
    }

    fun changeLanguage(language: String) {
        when (language) {
            "English" -> {
                systemLanguageChange("en")
                languageSelectedListener?.onLanguageSelected("English")
            }

            "Kazakh" -> {
                systemLanguageChange("kk")
                languageSelectedListener?.onLanguageSelected("Қазақша")
            }

            "Russian" -> {
                systemLanguageChange("ru")
                languageSelectedListener?.onLanguageSelected("Русский")
            }
        }
    }

    fun systemLanguageChange(codeLanguage: String) {
        SharedProvider(requireContext()).saveLanguage(codeLanguage)
        val local = Locale(codeLanguage)
        Locale.setDefault(local)
        val config = Configuration()
        config.setLocale(local)
        requireContext().resources.updateConfiguration(
            config,
            requireContext().resources.displayMetrics
        )
        findNavController().navigate(
            R.id.profileFragment,
            arguments,
            NavOptions.Builder().setPopUpTo(R.id.profileFragment, true).build()
        )
    }

    fun selectedIcon(iconEnglish: Boolean, iconKazakh: Boolean, iconRussia: Boolean) {
        binding.apply {
            if (iconEnglish) {
                imgIconBtnSelectEnglish.visibility = View.VISIBLE
            } else {
                imgIconBtnSelectEnglish.visibility = View.GONE
            }
            if (iconKazakh) {
                imgIconBtnSelectKazakh.visibility = View.VISIBLE
            } else {
                imgIconBtnSelectKazakh.visibility = View.GONE
            }
            if (iconRussia) {
                imgIconBtnSelectRussia.visibility = View.VISIBLE
            } else {
                imgIconBtnSelectRussia.visibility = View.GONE
            }
        }
    }
}