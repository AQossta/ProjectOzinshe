package com.example.ozinsheexample.presentation.about

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozinsheexample.data.ApiService
import com.example.ozinsheexample.data.ServiceBuilder
import com.example.ozinsheexample.data.model.MovieByIdResponse
import com.example.ozinsheexample.data.model.Screenshot
import com.example.ozinsheexample.presentation.home.mainresponse.MainMoviesResponseItem
import com.example.ozinsheexample.presentation.home.mainresponse.modelmain.MoviesByCategoryMainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AboutViewModel: ViewModel() {
    private var _moviesByIdResponse: MutableLiveData<MovieByIdResponse> = MutableLiveData()
    val moviesByIdResponse: LiveData<MovieByIdResponse> = _moviesByIdResponse

    private var _errorResponse: MutableLiveData<String> = MutableLiveData()
    val errorResponse: LiveData<String> = _errorResponse

    fun getMoviesById(token: String, movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = ServiceBuilder.buildService(ApiService::class.java)
            runCatching { response.getMoviesById("Bearer $token", movieId) }
                .onSuccess {
                    _moviesByIdResponse.postValue(it)
                }
                .onFailure {
                    _errorResponse.postValue(it.message)
                }
        }
    }
}