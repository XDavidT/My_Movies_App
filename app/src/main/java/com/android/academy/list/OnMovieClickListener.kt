package com.android.academy.list

import com.android.academy.movie_model.MovieModel

interface OnMovieClickListener{
    fun onMovieClicked(movieModel: MovieModel)
}