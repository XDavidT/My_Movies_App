package com.android.academy.networking


import com.google.gson.annotations.SerializedName

data class VideoResult(
    @SerializedName("id")
    val id: Int,
    @SerializedName("results")
    val results: List<MovieVideoResult>
){
    companion object{
        private const val BASE_URL = "https://www.youtube.com/watch?v="
    }
    fun getDefaultTrailer():String{
        return BASE_URL + results[0].key
    }
}