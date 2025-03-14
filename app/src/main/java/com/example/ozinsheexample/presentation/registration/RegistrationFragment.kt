//package com.example.ozinsheexample.presentation.registration
//
//import android.os.Bundle
//import android.text.method.HideReturnsTransformationMethod
//import android.text.method.PasswordTransformationMethod
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Toast
//import androidx.fragment.app.viewModels
//import androidx.navigation.fragment.findNavController
//import com.example.ozinsheexample.R
//import com.example.ozinsheexample.databinding.FragmentLoginBinding
//import com.example.ozinsheexample.databinding.FragmentRegistrationBinding
//
//class RegistrationFragment : Fragment() {
//
//    private lateinit var binding: FragmentRegistrationBinding
//    private val viewModel: RegistrationViewModel by viewModels()
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = FragmentRegistrationBinding.inflate(layoutInflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        viewModel.registrationResponse.observe(viewLifecycleOwner) {
//            binding.tvErrorTextPasswordAndServer.visibility = View.GONE
//            Toast.makeText(requireContext(), "Сәтті кіру!", Toast.LENGTH_SHORT).show()
//
//            findNavController().navigate(R.id.)
//        }
//
//        binding.btnShowPassword.setOnClickListener{
//            togglePasswordRegisterVisibility()
//        }
//        binding.btnRepeatShowPassword.setOnClickListener{
//            togglePasswordRegisterVisibility()
//        }
//
//        binding.btnRegister.setOnClickListener{
//            val email = binding.editTextEmail.text.toString()
//            val password = binding.editTextPassword.text.toString()
//
//            val isValidEmail = emailValidation(email)
//            val isValidPassword = validationPasswordRegister(password)
//
//            if(isValidEmail && isValidPassword){
//                viewModel.registerUser(email, password)
//            }else{
//                validationEmailRegister(isValidEmail)
//            }
//
//        }
//    }
//
//
//    fun togglePasswordRegisterVisibility() {
//        val passwordEditText = binding.editTextPassword
//        passwordEditText.transformationMethod = if (passwordEditText.transformationMethod == HideReturnsTransformationMethod.getInstance()) {
//            PasswordTransformationMethod.getInstance()
//        } else {
//            HideReturnsTransformationMethod.getInstance()
//        }
//    }
//
//    val emailPatternRegister = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
//    fun emailValidation(email: String): Boolean {
//        return email.trim().matches(emailPatternRegister.toRegex())
//    }
//
//    fun validationEmailRegister(isValid: Boolean) {
//        if(isValid){
//            binding.tvErrorTextEmail.text = ""
//            binding.tvErrorTextEmail.visibility = View.GONE
//            binding.editTextEmail.setBackgroundResource(R.drawable.background_edittext_focus_action_12dp)
//        }else{
//            binding.tvErrorTextEmail.text = "Қате формат"
//            binding.tvErrorTextEmail.visibility = View.VISIBLE
//            binding.editTextEmail.setBackgroundResource(R.drawable.background_edittext_12dp_error)
//        }
//    }
//
//    fun validationPasswordRegister(password: String): Boolean {
//        return if(password.length < 6){
//            binding.tvErrorTextPasswordAndServer.text = "Құпия сөз ұзындығы 6 таңбадан кем емес"
//            binding.tvErrorTextPasswordAndServer.visibility = View.VISIBLE
//            binding.editTextPassword.setBackgroundResource(R.drawable.background_edittext_12dp_error)
//            false
//        }else{
//            binding.tvErrorTextPasswordAndServer.visibility = View.GONE
//            binding.editTextPassword.setBackgroundResource(R.drawable.background_edittext_focus_action_12dp)
//            true
//        }
//    }
//}
//
