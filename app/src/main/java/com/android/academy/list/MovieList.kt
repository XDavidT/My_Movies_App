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
                        "the violence of oppressors meets the rage of the others."
            )
        )
        movies.add(
            MovieModel(
                "Deadpool 2",
                R.drawable.deadpool2,
                "Deadpool protects a young mutant Russell from the authorities and" +
                        " gets thrown in prison. However, he escapes and forms a team of mutants" +
                        " to prevent a time-travelling mercenary from killing Russell."
            )
        )
        movies.add(
            MovieModel(
                "Guardians of the Galaxy",
                R.drawable.guardiansofthegalaxy,
                "Peter escapes from the planet Morag with a valuable orb that Ronan" +
                        " the Accuser wants. He eventually forms a group with unwilling heroes" +
                        " to stop Ronan."
            )
        )
        movies.add(
            MovieModel(
                "Ocean's 8",
                R.drawable.oceaneight,
                "Debbie Ocean is released from jail after serving a prison sentence." +
                        " She assembles a special crew of seven women to steal a diamond necklace," +
                        " worth 150 million dollars, from the Met Gala."
            )
        )
        movies.add(
            MovieModel(
                "Thor: Ragnarok",
                R.drawable.thor,
                "Deprived of his mighty hammer Mjolnir, Thor must escape the" +
                        " other side of the universe to save his home, Asgard, from Hela," +
                        " the goddess of death."
            )
        )
    }
}
