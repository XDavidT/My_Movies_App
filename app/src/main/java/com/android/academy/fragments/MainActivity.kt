package com.android.academy.fragments

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.academy.DetailActivity
import com.android.academy.R
import com.android.academy.list.OnMovieClickListener
import com.android.academy.movie_model.MovieModel


class MainActivity : AppCompatActivity(), OnMovieClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val moviesFragment = MoviesFragment()

        //Starting Activity using list of movies in fragment ( in main frame )
        supportFragmentManager.beginTransaction().
            add(R.id.activity_main_frame,moviesFragment).commit()
    }

    override fun onMovieClicked(movie:MovieModel){
        DetailActivity.open(this,2)
//        val detailsFragment = DetailsFragment.newInstance(movie)
//        //When user click movie, change the details fragment
//        supportFragmentManager.beginTransaction().apply {
//         addToBackStack(null)
//         replace(R.id.activity_main_frame,detailsFragment)
//        }.commit()
    }
}