package com.android.academy.movie_model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
@Entity
@Parcelize
data class MovieModel(
    @PrimaryKey
    val id: Int,
    val name: String,
    val imagePoster: String,
    val imageCover: String,
    val releaseDate: String,
    var trailerUrl: String,
    val description: String?,
    val popularity: Double
) : Parcelable