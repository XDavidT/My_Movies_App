package com.android.academy.movie_data

import android.os.Parcelable
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