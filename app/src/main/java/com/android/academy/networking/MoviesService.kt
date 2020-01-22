package com.android.academy.networking

import MoviesRootResult
import com.android.academy.R
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface MoviesService {
    companion object {
        private const val BASE_URL = "https://api.themoviedb.org"
        const val BASE_API_URL = "$BASE_URL/3/"
        private const val POPULAR_MOVIES = "movie/popular"

        private const val KEQ_QUERY = "?api_key=add9f6bd814488dd0c9172351b2a9f66"

        private const val popularMoviesQuery = POPULAR_MOVIES + KEQ_QUERY
    }

    @GET(popularMoviesQuery)
    fun loadPopularMovies(): Call<MoviesRootResult>
}

object RestClient {

    private val retrofitClient = Retrofit.Builder().baseUrl(MoviesService.BASE_API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val moviesService = retrofitClient.create(MoviesService::class.java)

    fun getPopularMovies(): Call<MoviesRootResult> {
        return moviesService.loadPopularMovies()
    }
}