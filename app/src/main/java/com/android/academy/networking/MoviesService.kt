package com.android.academy.networking

import com.android.academy.R
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface MoviesService {
    companion object {
        private const val BASE_URL = "https://api.themoviedb.org"
        const val BASE_API_URL = "$BASE_URL/3/"
        private const val MOVIES_PATH = "movie/"
        private const val POPULAR_MOVIES = MOVIES_PATH+"popular"
        private const val VIDEOS_SUFFIX = "/videos"
        private const val KEQ_QUERY = "?api_key=add9f6bd814488dd0c9172351b2a9f66"

        private const val popularMoviesQuery = POPULAR_MOVIES + KEQ_QUERY
    }

    @GET(popularMoviesQuery)
    fun loadPopularMovies(): Call<MoviesRootResult>

    @GET("$MOVIES_PATH{movie_id}$VIDEOS_SUFFIX$KEQ_QUERY")
    fun loadVideosToMovie(@Path("movie_id") movie_id:Int): Call<VideoResult>
}

object RestClient {

    private val retrofitClient = Retrofit.Builder().baseUrl(MoviesService.BASE_API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val moviesService = retrofitClient.create(MoviesService::class.java)

    fun getPopularMovies(): Call<MoviesRootResult> {
        return moviesService.loadPopularMovies()
    }

    fun getVideosToMovies(movie_id: Int):Call<VideoResult>{
        return moviesService.loadVideosToMovie(movie_id)
    }
}