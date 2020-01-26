package com.android.academy.movie_model

import android.util.SparseArray
import androidx.core.util.forEach

object MoviesContent {
//    val movies = ArrayList<MovieModel>()
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
}