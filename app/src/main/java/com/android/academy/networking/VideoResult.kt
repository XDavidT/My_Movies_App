package com.android.academy.networking


import com.google.gson.annotations.SerializedName

data class VideoResult(
    @SerializedName("id")
    val id: Int,
    @SerializedName("results")
    val results: List<MovieVideoResult>
)