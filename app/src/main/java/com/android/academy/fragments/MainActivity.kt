package com.android.academy.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import com.android.academy.R
import com.android.academy.fragments.DetailsFragment
import com.android.academy.fragments.MoviesFragment
import com.android.academy.list.OnMovieClickListener
import com.android.academy.movie_model.MovieModel


class MainActivity : AppCompatActivity(), OnMovieClickListener {
    private var tabletFragmentContainer: FrameLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tabletFragmentContainer = findViewById(R.id.activity_main_tablet_frame)
        val moviesFragment = MoviesFragment()

        supportFragmentManager.beginTransaction().
            add(R.id.activity_main_frame,moviesFragment).commit()
    }

    override fun onMovieClicked(movie:MovieModel){
        val detailsFragment = DetailsFragment.newInstance(movie)
     supportFragmentManager.beginTransaction().apply {
         if( tabletFragmentContainer == null){
             addToBackStack(null)
             replace(R.id.activity_main_frame,detailsFragment)
         }
         else{
             replace(R.id.activity_main_tablet_frame,detailsFragment)
         }
     }.commit()
    }
}