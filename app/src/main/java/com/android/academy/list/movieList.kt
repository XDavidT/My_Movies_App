package com.android.academy.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.academy.R
import com.android.academy.movie_model.MovieModel

class movieList : AppCompatActivity() {

    private val movies = mutableListOf<MovieModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)
    }

    fun loadMovies(){
        movies.add(
            MovieModel(
            "Jurassic Park",
            R.drawable.jurassic_world_fallen_kingdom,
            "After a volcano eruption proves to be a threat for the dinosaurs, " +
                    "Owen and Claire reach the defunct Jurassic World, a theme park, " +
                    "to save the animals from extinction."
            )
        )
        movies.add(
            MovieModel(
                "The Meg",
                R.drawable.the_meg,
                "When the members of an underwater research facility are under threat " +
                        "owing to a 75 feet prehistoric shark, Jonas Taylor, " +
                        "a deep sea diver, is hired to save them."
            )
        )
    }
}
