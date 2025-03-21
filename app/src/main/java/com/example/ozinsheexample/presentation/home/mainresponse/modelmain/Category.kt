package com.example.ozinsheexample.presentation.home.mainresponse.modelmain


import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("id")
    val id: Int, // 8
    @SerializedName("name")
    val name: String, // Толықметрлі мультфильмдер
    @SerializedName("fileId")
    val fileId: Any, // null
    @SerializedName("link")
    val link: Any, // null
    @SerializedName("movieCount")
    val movieCount: Int // 2
)