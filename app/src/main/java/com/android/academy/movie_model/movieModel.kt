package com.android.academy.movie_model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieModel(
    val name: String,
    @DrawableRes val imageRes: Int,
    val description: String?
) : Parcelable