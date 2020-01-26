package com.android.academy.details

import android.graphics.Movie
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.android.academy.movie_model.MoviesContent

class SimplePagerAdapter ( mananger: FragmentManager):
        FragmentPagerAdapter(mananger) {
    override fun getItem(position: Int): Fragment {
        val movieId = MoviesContent.movies.keyAt(position) // Find ID by position
        return DetailsFragment.newInstance(MoviesContent.movies.get(movieId))
    }

    override fun getCount(): Int {
        return MoviesContent.movies.size()
    }
}