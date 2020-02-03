package com.android.academy.networking


import com.android.academy.movie_data.VideoModel
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

    fun covnertToVideoModel() : VideoModel? {
        if (!results.isEmpty()) {
            val firstResult = results[0]
            return VideoModel(id, firstResult.id, firstResult.key)
        }
        return null
    }
}