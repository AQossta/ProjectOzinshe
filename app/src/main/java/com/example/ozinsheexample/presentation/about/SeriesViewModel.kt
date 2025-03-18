package com.example.ozinsheexample.presentation.about

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozinsheexample.data.ApiService
import com.example.ozinsheexample.data.ServiceBuilder
import com.example.ozinsheexample.data.model.MovieByIdResponse
import com.example.ozinsheexample.presentation.about.videorespose.VideoResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SeriesViewModel: ViewModel() {
    private var _videosResponse: MutableLiveData<List<VideoResponse>> = MutableLiveData()
    val videosResponse: LiveData<List<VideoResponse>> = _videosResponse

    private var _errorResponse: MutableLiveData<String> = MutableLiveData()
    val errorResponse: LiveData<String> = _errorResponse

    fun getSeries(token: String, movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = ServiceBuilder.buildService(ApiService::class.java)
            runCatching { response.getSeries("Bearer $token", movieId) }
                .onSuccess {
                    _videosResponse.postValue(it)
                }
                .onFailure {
                    _errorResponse.postValue(it.message)
                }
        }
    }
}