package com.example.ozinsheexample.presentation.about

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozinsheexample.data.ApiService
import com.example.ozinsheexample.data.ServiceBuilder
import com.example.ozinsheexample.data.model.MovieByIdResponse
import com.example.ozinsheexample.data.model.MovieIdModel
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

    private var _moviesAddResponse: MutableLiveData<MovieIdModel> = MutableLiveData()
    val moviesAddResponse: LiveData<MovieIdModel> = _moviesAddResponse

    private var _moviesDeleteResponse: MutableLiveData<String> = MutableLiveData()
    val moviesDeleteResponse: LiveData<String> = _moviesDeleteResponse

    private var _favoriteState: MutableLiveData<Boolean> = MutableLiveData()
    val favoriteState: LiveData<Boolean> = _favoriteState


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

    fun addFavorite(token: String, movieId: MovieIdModel) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = ServiceBuilder.buildService(ApiService::class.java)
            runCatching { response.addFavorite("Bearer $token", movieId) }
                .onSuccess {
                    Log.d("AAA", "addFavorite: ${it}")
                    _favoriteState.postValue(true)
                }
                .onFailure {
                    _errorResponse.postValue(it.message)
                }
        }
    }

    fun deleteFavorite(token: String, movieId: MovieIdModel) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = ServiceBuilder.buildService(ApiService::class.java)
            runCatching { response.deleteFavorite("Bearer $token", movieId) }
                .onSuccess {
                    Log.d("AAA", "deleteFavorite: ${it}")
                    _favoriteState.postValue(false)
                }
                .onFailure {
                    _errorResponse.postValue(it.message)
                }
        }
    }
}