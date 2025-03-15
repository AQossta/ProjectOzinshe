package com.example.ozinsheexample.presentation.home.mainresponse.modelmain


import com.google.gson.annotations.SerializedName

data class MoviesByCategoryMainModelItem(
    @SerializedName("categoryId")
    val categoryId: Int, // 8
    @SerializedName("categoryName")
    val categoryName: String, // Толықметрлі мультфильмдер
    @SerializedName("movies")
    val movies: List<Movy>
)