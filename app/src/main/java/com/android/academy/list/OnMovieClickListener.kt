package com.android.academy.list

import com.android.academy.movie_data.MovieModel

interface OnMovieClickListener{
    fun onMovieClicked(movieModel: MovieModel)
}