package com.android.academy.movie_model

import androidx.annotation.DrawableRes

data class MovieModel(
    val name: String,
    @DrawableRes val imageRes: Int,
    val description: String?
)