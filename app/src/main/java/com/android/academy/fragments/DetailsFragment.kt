package com.android.academy.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.android.academy.R
import com.android.academy.list.OnMovieClickListener
import com.android.academy.movie_model.MovieModel

private const val MOVIE_BUNDLE_KEY= "unique_movie_key"

class DetailsFragment : Fragment(){
    private lateinit var posterImage: ImageView
    private lateinit var titleText: TextView
    private lateinit var releaseDate: TextView
    private lateinit var trailerButton: Button
    private lateinit var overviewText: TextView

    companion object{
        fun newInstance(movie: MovieModel): DetailsFragment{
            val fragment = DetailsFragment()
            val args = Bundle()
            args.putParcelable(MOVIE_BUNDLE_KEY,movie)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.details_fragment_poster,container,false)


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movie:MovieModel? = arguments?.getParcelable(MOVIE_BUNDLE_KEY)
        initViews(view)
        movie?.let(::loadMovie)
        Log.d("David","onViewCreated - details fragment")

    }


    fun loadMovie(movie:MovieModel){
        posterImage.setImageResource(movie.imageRes)
        titleText.text = movie.name
        overviewText.text = movie.description
    }

    private fun initViews(view:View){
        posterImage = view.findViewById(R.id.posterImage)
        titleText = view.findViewById(R.id.movie_title)
        releaseDate = view.findViewById(R.id.relaseDate)
        trailerButton = view.findViewById(R.id.trailer_button)
        overviewText = view.findViewById(R.id.overview)
    }
}