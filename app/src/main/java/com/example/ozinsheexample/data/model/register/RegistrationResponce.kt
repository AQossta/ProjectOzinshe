package com.example.ozinsheexample.data.model.register


import com.google.gson.annotations.SerializedName

data class RegistrationResponce(
    @SerializedName("id")
    val id: Int, // 25905
    @SerializedName("username")
    val username: String, // ABCdefg@gmail.com
    @SerializedName("email")
    val email: String, // ABCdefg@gmail.com
    @SerializedName("roles")
    val roles: List<String>,
    @SerializedName("accessToken")
    val accessToken: String, // eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBQkNkZWZnQGdtYWlsLmNvbSIsImlhdCI6MTc0MTYyNjg2MywiZXhwIjoxNzczMTYyODYzfQ.MBahAWzNx0g0zMcEOJyPh3uzrTGcgF_0CAzAg6wUM7mrVdz-hxAbZ_J00fs-6ceffq9qXrLlpspHd4ZbOeA75g
    @SerializedName("tokenType")
    val tokenType: String // Bearer
)