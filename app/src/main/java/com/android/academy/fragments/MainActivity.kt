package com.android.academy.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.academy.R
import com.android.academy.fragments.DetailsFragment
import com.android.academy.fragments.MoviesFragment
import com.android.academy.list.OnMovieClickListener
import com.android.academy.movie_model.MovieModel


class MainActivity : AppCompatActivity(), OnMovieClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val moviesFragment = MoviesFragment()

        supportFragmentManager.beginTransaction().
            add(R.id.activity_main_frame,moviesFragment).commit()
    }

    override fun onMovieClicked(movie:MovieModel){
        val detailsFragment = DetailsFragment()
     supportFragmentManager.beginTransaction().replace(R.id.activity_main_frame,detailsFragment).commit()
    }
}