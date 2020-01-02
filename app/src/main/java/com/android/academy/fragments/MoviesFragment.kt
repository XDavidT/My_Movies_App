package com.android.academy.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.academy.R
import com.android.academy.list.MoviesAdapter
import com.android.academy.list.OnMovieClickListener
import com.android.academy.movie_model.MovieContentManager
import com.android.academy.movie_model.MovieModel
import kotlinx.android.synthetic.main.movies_rv_fragment.*

class MoviesFragment :Fragment(), OnMovieClickListener{
    private var listener: OnMovieClickListener? = null
    private lateinit var movieAdapter: MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.movies_rv_fragment,container,false)

        Log.d("David","On create view")
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadMovies()
        initRecyclerView()
        Log.d("David","onViewCreated")

    }

    override fun onMovieClicked(movie: MovieModel) {
        listener?.onMovieClicked(movie)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if(context is OnMovieClickListener){
            listener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    private fun initRecyclerView(){

        Log.d("David","Init recycler view")
        movies_fragment_rcv.layoutManager = LinearLayoutManager(context)
        movieAdapter = MoviesAdapter(this@MoviesFragment)
        movies_fragment_rcv.adapter = movieAdapter
        movieAdapter.setData(MovieContentManager.movies)
    }

    fun loadMovies(){
        MovieContentManager.addMovie(
            MovieModel(
                "Jurassic Park",
                R.drawable.jurassic_world_fallen_kingdom,
                "After a volcano eruption proves to be a threat for the dinosaurs, " +
                        "Owen and Claire reach the defunct Jurassic World, a theme park, " +
                        "to save the animals from extinction."
            )
        )
        MovieContentManager.addMovie(
            MovieModel(
                "The Meg",
                R.drawable.the_meg,
                "When the members of an underwater research facility are under threat " +
                        "owing to a 75 feet prehistoric shark, Jonas Taylor, " +
                        "a deep sea diver, is hired to save them."
            )
        )
        MovieContentManager.addMovie(
            MovieModel(
                "The First Purge",
                R.drawable.the_first_purge,
                "To push the crime rate below one percent for the rest of the year, " +
                        "the New Founding Fathers of America test a sociological theory that" +
                        " vents aggression for one night in one isolated community. But when " +
                        "the violence of oppressors meets the rage of the others."
            )
        )
        MovieContentManager.addMovie(
            MovieModel(
                "Deadpool 2",
                R.drawable.deadpool2,
                "Deadpool protects a young mutant Russell from the authorities and" +
                        " gets thrown in prison. However, he escapes and forms a team of mutants" +
                        " to prevent a time-travelling mercenary from killing Russell."
            )
        )
        MovieContentManager.addMovie(
            MovieModel(
                "Guardians of the Galaxy",
                R.drawable.guardiansofthegalaxy,
                "Peter escapes from the planet Morag with a valuable orb that Ronan" +
                        " the Accuser wants. He eventually forms a group with unwilling heroes" +
                        " to stop Ronan."
            )
        )
        MovieContentManager.addMovie(
            MovieModel(
                "Ocean's 8",
                R.drawable.oceaneight,
                "Debbie Ocean is released from jail after serving a prison sentence." +
                        " She assembles a special crew of seven women to steal a diamond necklace," +
                        " worth 150 million dollars, from the Met Gala."
            )
        )
        MovieContentManager.addMovie(
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