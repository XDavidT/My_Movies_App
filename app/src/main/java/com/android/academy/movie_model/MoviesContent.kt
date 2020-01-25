package com.android.academy.movie_model

object MoviesContent {
    val movies = ArrayList<MovieModel>()

    fun clear(){
        movies.clear()
    }

    fun addMovie(movie: MovieModel){
        movies.add(movie)
    }

    fun addMovieList(movieList: List<MovieModel>){
        clear()
        movies.addAll(movieList)
    }
}