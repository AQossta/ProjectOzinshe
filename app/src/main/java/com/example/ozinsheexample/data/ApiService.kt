package com.example.ozinsheexample.data

import com.example.ozinsheexample.data.model.MovieByIdResponse
import com.example.ozinsheexample.data.model.MovieIdModel
import com.example.ozinsheexample.data.model.login.LoginRequest
import com.example.ozinsheexample.data.model.login.LoginResponse
import com.example.ozinsheexample.data.model.register.RegistrationRequest
import com.example.ozinsheexample.data.model.register.RegistrationResponce
import com.example.ozinsheexample.presentation.about.videorespose.VideoResponse
import com.example.ozinsheexample.presentation.home.mainresponse.MainMoviesResponse
import com.example.ozinsheexample.presentation.home.mainresponse.MainMoviesResponseItem
import com.example.ozinsheexample.presentation.home.mainresponse.modelmain.MoviesByCategoryMainModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET("/core/V1/movies_main")
    suspend fun getMainMovies(@Header("Authorization") token: String): MainMoviesResponse

    @POST("/auth/V1/signin")
    suspend fun loginUser(@Body request: LoginRequest): LoginResponse

    @POST("/auth/V1/signup")
    suspend fun registerUser(@Body request: RegistrationRequest): RegistrationResponce

    @GET("/core/V1/movies/main")
    suspend fun getMainMovieByCategory(@Header("Authorization") token: String): MoviesByCategoryMainModel

    @GET("/core/V1/movies/{id}")
    suspend fun getMoviesById(
        @Header("Authorization") token: String,
        @Path("id") id: Int): MovieByIdResponse

    @GET("/core/V1/seasons/{Id}")
    suspend fun getSeries(
        @Header("Authorization") token: String,
        @Path("id") id: Int): List<VideoResponse>

    @POST("/core/V1/favorite")
    suspend fun addFavorite(
        @Header(value = "Authorization") token: String,
        @Body movieBody: MovieIdModel
    ): MovieIdModel

    @HTTP(method = "DELETE", path = "/core/V1/favorite/", hasBody = true)
    suspend fun deleteFavorite(
        @Header(value = "Authorization") token: String,
        @Body movieBody: MovieIdModel
    ): Unit
}