package com.android.academy.movie_model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieModel(
    val name: String,
    val imagePoster: String,
    val imageCover: String,
    val releaseDate: String,
    val trailerUrl: String,
    val description: String?
) : Parcelable