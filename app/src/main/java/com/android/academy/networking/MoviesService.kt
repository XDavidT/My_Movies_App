package com.android.academy.networking

import MoviesResult
import com.android.academy.R
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface MoviesService {
    companion object{
        private const val BASE_URL = "https://api.themoviedb.org"
        const val BASE_API_URL = "$BASE_URL/3"
        private const val POPULAR_MOVIES = "/movie/popular"

        private const val KEQ_QUERY = "?api_key=${R.string.themoviedb_api_v3}"

        private const val popularMoviesQuery = POPULAR_MOVIES+ KEQ_QUERY
    }

    @GET(popularMoviesQuery)
    abstract fun loadPopularMovies(): Call<MoviesResult>
}

object RestClient {

    val retrofit = Retrofit.Builder().baseUrl(MoviesService.BASE_API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val moviesService =  retrofit.create(MoviesService::class.java)
}