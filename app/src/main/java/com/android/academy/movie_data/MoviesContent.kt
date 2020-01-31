package com.android.academy.movie_data

import android.util.SparseArray
import androidx.core.util.forEach

object MoviesContent {
    var movies = SparseArray<MovieModel>()

    fun getMoviesList(): ArrayList<MovieModel>{
        val moviesList = ArrayList<MovieModel>()
        movies.forEach { _, movie ->
            moviesList.add(movie)
        }
        return moviesList
    }

    fun clear(){
        movies.clear()
    }

    fun addMovieList(movieList: SparseArray<MovieModel>){
        clear()
        movies = movieList
    }

    fun setTrailer(movieID: Int,trailerUrl: String){
        val currentMovie = movies.get(movieID)
        currentMovie.trailerUrl = trailerUrl
        movies.put(movieID,currentMovie)
    }
}