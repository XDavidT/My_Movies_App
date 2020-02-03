package com.android.academy.movie_data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MovieModel::class, VideoModel::class],version = 2)
abstract class AppDatabase : RoomDatabase(){

    abstract fun movieDao(): MovieDao?
    abstract fun trailerDao(): TrailerDao?

    companion object{
        private const val DATABASE_NAME = "movies"
        private var INSTANCE : AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if(INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                )
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }
}