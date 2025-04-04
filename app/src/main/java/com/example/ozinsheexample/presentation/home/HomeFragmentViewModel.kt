package com.example.ozinsheexample.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozinsheexample.data.ApiService
import com.example.ozinsheexample.data.ServiceBuilder
import com.example.ozinsheexample.presentation.home.mainresponse.MainMoviesResponse
import com.example.ozinsheexample.presentation.home.mainresponse.modelmain.MoviesByCategoryMainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeFragmentViewModel: ViewModel() {
    private var _mainMoviesResponse: MutableLiveData<MainMoviesResponse> = MutableLiveData()
    val mainMoviesResponse: LiveData<MainMoviesResponse> = _mainMoviesResponse

    private var _moviesByCategoryMainModel: MutableLiveData<MoviesByCategoryMainModel> = MutableLiveData()
    val moviesByCategoryMainModel: LiveData<MoviesByCategoryMainModel> = _moviesByCategoryMainModel

    private var _errorResponse: MutableLiveData<String> = MutableLiveData()
    val errorResponse: LiveData<String> = _errorResponse

    fun getMainMovies(token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = ServiceBuilder.buildService(ApiService::class.java)
            runCatching { response.getMainMovies("Bearer $token") }
                .onSuccess {
                    _mainMoviesResponse.postValue(it)
                }
                .onFailure {
                    _errorResponse.postValue(it.message)
                }
        }
    }

    fun getMoviesByCategoryMain(token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = ServiceBuilder.buildService(ApiService::class.java)
            runCatching { response.getMainMovieByCategory("Bearer $token") }
                .onSuccess {
                    _moviesByCategoryMainModel.postValue(it)
                }
                .onFailure {
                    _errorResponse.postValue(it.message)
                }
        }
    }
}