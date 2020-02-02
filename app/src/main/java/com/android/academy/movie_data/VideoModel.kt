package com.android.academy.movie_data

import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity
data class VideoModel(
    @PrimaryKey
    val movie_id: Int,
    val id: String,
    val key: String
)