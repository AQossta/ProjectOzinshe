//package com.example.ozinsheexample.presentation.registration
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.ozinsheexample.data.ApiService
//import com.example.ozinsheexample.data.ServiceBuilder
//import com.example.ozinsheexample.data.model.register.RegistrationRequest
//import com.example.ozinsheexample.data.model.register.RegistrationResponce
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//
//class RegistrationViewModel(): ViewModel() {
//    private var _registrationResponse: MutableLiveData<RegistrationResponce> = MutableLiveData()
//    val registrationResponse: LiveData<RegistrationResponce> = _registrationResponse
//
//    private var _errorResponse: MutableLiveData<String> = MutableLiveData()
//    val errorResponse: LiveData<String> = _errorResponse
//
//    fun loginUser(email: String, password: String) {
//        viewModelScope.launch(Dispatchers.IO) {
//            val response = ServiceBuilder.buildService(ApiService::class.java)
//            runCatching { response.registerUser(RegistrationRequest(email, password)) }
//                .onSuccess {
//                    _registrationResponse.postValue(it)
//                }
//                .onFailure {
//                    _errorResponse.postValue(it.message)
//                }
//        }
//    }
//}