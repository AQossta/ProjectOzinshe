package com.example.ozinsheexample.presentation.about.videorespose


import com.google.gson.annotations.SerializedName

data class Video(
    @SerializedName("id")
    val id: Int, // 592
    @SerializedName("link")
    val link: String, // t3j_QNz_RUg
    @SerializedName("seasonId")
    val seasonId: Int, // 137
    @SerializedName("number")
    val number: Int // 1
)