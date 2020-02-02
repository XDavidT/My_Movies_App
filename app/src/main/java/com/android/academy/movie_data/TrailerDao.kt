package com.android.academy.movie_data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TrailerDao {

    @Query("SELECT * FROM VideoModel WHERE movie_id == :movieId LIMIT 1")
    fun getTrailer(movieId:Int) : VideoModel?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTrailer(videoModel: VideoModel?)
}