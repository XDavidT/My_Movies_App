package com.android.academy.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.academy.R
import com.android.academy.movie_model.MovieModel
import kotlinx.android.synthetic.main.activity_movie_list.*

class MovieList : AppCompatActivity() {

    private val movies = mutableListOf<MovieModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)
        loadMovies()
        initRecyView()
    }

    private fun initRecyView(){

        //New layout manager to get the view in linear
        movies_list_layout.layoutManager = LinearLayoutManager(this)

        //build adapter with local context
        var moviesAdapter = MoviesAdapter(this)

        //attach the adapter
        movies_list_layout.adapter = moviesAdapter

        //add some data
        moviesAdapter.setData(movies)

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
        movies.add(
            MovieModel(
                "The First Purge",
                R.drawable.the_first_purge,
                "To push the crime rate below one percent for the rest of the year, " +
                        "the New Founding Fathers of America test a sociological theory that" +
                        " vents aggression for one night in one isolated community. But when " +
                        "the violence of oppressors meets the rage of the others, the contagion" +
                        " will explode from the trial-city borders and spread across the nation."
            )
        )
    }
}
