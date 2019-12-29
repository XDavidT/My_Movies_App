package com.android.academy.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.android.academy.R
import com.android.academy.list.OnMovieClickListener

class DetailsFragment : Fragment(){
    private lateinit var posterImage: ImageView
    private lateinit var titleText: TextView
    private lateinit var releaseDate: TextView
    private lateinit var trailerButton: Button
    private lateinit var overviewText: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.details_fragment_poster,container,false)
        initViews(view)

        return super.onCreateView(inflater, container, savedInstanceState)
    }



    private fun initViews(view:View){
        posterImage = view.findViewById(R.id.posterImage)
        titleText = view.findViewById(R.id.movie_title)
        releaseDate = view.findViewById(R.id.relaseDate)
        trailerButton = view.findViewById(R.id.trailer_button)
        overviewText = view.findViewById(R.id.overview)
    }
}