package com.example.ozinsheexample.data

import com.example.ozinsheexample.data.model.login.LoginRequest
import com.example.ozinsheexample.data.model.login.LoginResponse
import com.example.ozinsheexample.data.model.register.RegistrationRequest
import com.example.ozinsheexample.data.model.register.RegistrationResponce
import com.example.ozinsheexample.presentation.home.mainresponse.MainMoviesResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @GET("/core/V1/movies_main")
    suspend fun getMainMovies(@Header("Authorization") token: String): MainMoviesResponse

    @POST("/auth/V1/signin")
    suspend fun loginUser(@Body request: LoginRequest): LoginResponse

    @POST("/auth/V1/signup")
    suspend fun registerUser(@Body request: RegistrationRequest): RegistrationResponce
}