package com.android.academy.movie_data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDao {

    @Query("SELECT * FROM MovieModel ORDER BY popularity DESC")
    fun getAll(): List<MovieModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movies: Collection<MovieModel>)

    @Query("DELETE From MovieModel")
    fun deleteAll()
}